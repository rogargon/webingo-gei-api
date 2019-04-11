package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CardStepDefs {
    @Autowired
    CardRepository cr;
    @Autowired
    GameRepository gr;
    @Autowired
    PlayerRepository pr;
    private String idc;
    private List<Card> cards;
    private Exception actualException;
    private Card created;


    private final StepDefs stepDefs;

    public CardStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @Given("^There is a game with price (.+) and id (\\d+)$")
    public void thereIsAGame(double arg, int arg2) {
        // Create game
        Game g = new Game();
        g.setId(arg2);
        g.setPricePerCard(arg);
        try {
            System.out.println(g);
            gr.save(g);
        }catch (Exception e){
            actualException = e;
        }
    }

    @When("^I join the Game with id (\\d+) as user \"([^\"]*)\"$")
    public void iJoinTheGameWithIdAsUser(int arg0, String arg1) throws Throwable {
        // Create card
        Card c = new Card();
        c.setId(arg0);
        // Set game reference
        if(gr.findById(arg0).isPresent()){
            c.setGame(gr.findById(arg0).get());
        }else{
            throw new Exception("The game with id " + arg0 +" does not exist");
        }

        // Find player by ID (username)
        Player p = pr.findById(arg1).orElse(null);
        if(p != null) p.setCard(c);
        else throw new Exception("The user with id " + arg1 +" does not exist");

        // Post new Card
        String json = stepDefs.mapper.writeValueAsString(c);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        idc = stepDefs.result.andReturn().getResponse().getHeader("Location");

        JSONObject player = new JSONObject();
        player.put("card", "/cards/" + String.valueOf(c.getId()));
        stepDefs.mockMvc.perform(
                patch("/players/{username}",arg1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("^A card has been created with price (.+) for the game with id (\\d+)$")
    public void CardCreated(double arg, int arg2) throws Exception{
        Assert.assertNotNull("Location not null", idc);
        // Get card
        stepDefs.result = stepDefs.mockMvc.perform(
                get(idc)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        String game = stepDefs.result.andReturn().getResponse().getContentAsString();

        // Get game
        game = (JsonPath.read(game, "$._links.game.href"));
        stepDefs.mockMvc.perform(
                get(game)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(jsonPath("$._links.self.href", endsWith("/games/" + arg2)));

    }

    @Then("^A \"([^\"]*)\" occurs$")
    public void aOccurs(String arg0) {
        Assert.assertEquals("Exception MissMatch", arg0,actualException.getClass().getSimpleName());
    }

    @And("^There is a card with id (\\d+) associated to the game with id (\\d+) created by player \"([^\"]*)\"$")
    public void thereIsACardWithIdAssociatedToTheGameWithId(int arg0, int arg1, String arg2) throws Exception {
        // Create card
        Card c = new Card();
        c.setId(arg0);

        // Set game
        if(gr.findById(arg1).isPresent()){
            c.setGame(gr.findById(arg1).get());
        }

        // Post new Card
        String json = stepDefs.mapper.writeValueAsString(c);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        // Find player by ID
        Player p = pr.findById(arg2).orElse(null);
        if(p != null) p.setCard(c);
        else throw new Exception("The user with id " + arg1 +" does not exist");

        JSONObject player = new JSONObject();
        player.put("card", "/cards/" + String.valueOf(c.getId()));
        stepDefs.mockMvc.perform(
                patch("/players/{username}",arg2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I delete a card with id (\\d+)$")
    public void iDeleteACardWithId(int arg0) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/cards/{id}", arg0)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The card with id (\\d+) does not exist$")
    public void theCardWithIdHasBeenDeleted(int arg0) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/cards/{id}", arg0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @And("^The card with id (\\d+) exists$")
    public void theCardWithIdHasNotBeenDeleted(int arg0) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/cards/{id}", arg0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @When("^I list the cards of the game with id (\\d+)$")
    public void iListTheCardsOfTheGameWithId(int arg0) throws Exception {
        if (gr.findById(arg0).isPresent()){
            cards = cr.findByGame(gr.findById(arg0).get());
            System.out.println(cards);
        }
    }

    @And("^There are (\\d+) cards associated$")
    public void thereAreCardsAssociated(int arg0) {
        assert arg0 == cards.size();
    }

    @Given("^A card is created$")
    public void aCardIsCreated() {
        created = new Card();
    }

    @When("^I generate the numbers$")
    public void iGenerateTheNumbers() {
        created.generateCard();
    }

    @Then("^The resulting card is generated properly$")
    public void theResultingCardIsGeneratedProperly() {
        int[][] numbers = created.getNumbers();
        List<Integer> rownums = new ArrayList<>();
        int[] max = new int[created.getCols()];
        int count = 0;
        for (int i = 0; i < created.getRows(); i++) {
            for (int j = 0; j < created.getCols(); j++) {
                if (numbers[i][j] == -1) {//Check three numbers missing per row
                    count++;
                    max[i]++;
                }
                if(numbers[i][j] != -1){
                    assert !rownums.contains(numbers[i][j]);//Check number was not generated before
                    rownums.add(numbers[i][j]);
                    assert numbers[i][j] < (j + 1) * 10;//Check range of generation is correct
                }
            }
            assert count == 3;
            count = 0;
        }
        for (int j = 1; j < created.getCols(); j++) {//Check there are at most two spaces for each column
            assert max[j] < 3;
        }
    }

    @When("^I list the cards of the user \"([^\"]*)\"$")
    public void iListTheCardsOfTheUser(String arg0) throws Throwable {
        // Get player
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", arg0)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        String card = stepDefs.result.andReturn().getResponse().getContentAsString();

        // Get card
        card = (JsonPath.read(card, "$._links.card.href"));
        stepDefs.result = stepDefs.mockMvc.perform(
                get(card)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
