package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CardStepDefs {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    private String idCard;
    private List<Card> cardList;
    private Exception actualException;
    private Card createdCard;
    private Game game;

    private final StepDefs stepDefs;

    public CardStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @Given("^There is a game with price (\\d+.\\d+) and id (\\d+)$")
    public void thereIsAGame(double pricePerCard, int id) {
        Game game = new Game();
        game.setId(id);
        game.setPricePerCard(pricePerCard);
        gameRepository.save(game);
    }

    @When("^I join the Game with id (\\d+) as user \"([^\"]*)\"$")
    public void iJoinTheGameWithIdAsUser(int game_id, String username) throws Throwable {
        // Create createdCard
        Card card = new Card();
        card.setId(game_id);
        // Set game reference
        if(gameRepository.findById(game_id).isPresent()){
            card.setGame(gameRepository.findById(game_id).get());
        }else{
            throw new Exception("The game with id " + game_id +" does not exist");
        }

        // Find player by ID (username)
        Player p = playerRepository.findById(username).orElse(null);
        if(p != null) p.setCard(card);
        else throw new Exception("The user with id " + username +" does not exist");

        // Post new Card
        String json = stepDefs.mapper.writeValueAsString(card);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        idCard = stepDefs.result.andReturn().getResponse().getHeader("Location");

        JSONObject player = new JSONObject();
        player.put("createdCard", "/cards/" + String.valueOf(card.getId()));
        player.put("played", new JSONArray().put("/games/" + game_id));
        stepDefs.mockMvc.perform(
                patch( "/players/{username}",username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("^A card has been created with price (.+) for the game with id (\\d+)$")
    public void CardCreated(double arg, int arg2) throws Exception{
        Assert.assertNotNull("Location not null", idCard);
        // Get createdCard
        stepDefs.result = stepDefs.mockMvc.perform(
                get(idCard)
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
        // Create createdCard
        Card c = new Card();
        c.setId(arg0);

        // Set game
        if(gameRepository.findById(arg1).isPresent()){
            c.setGame(gameRepository.findById(arg1).get());
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
        Player p = playerRepository.findById(arg2).orElse(null);
        if(p != null) p.setCard(c);
        else throw new Exception("The user with id " + arg1 +" does not exist");

        JSONObject player = new JSONObject();
        player.put("createdCard", "/cards/" + String.valueOf(c.getId()));
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
        if (gameRepository.findById(arg0).isPresent()){
            cardList = cardRepository.findByGame(gameRepository.findById(arg0).get());
            System.out.println(cardList);
        }
    }

    @And("^There are (\\d+) cards associated$")
    public void thereAreCardsAssociated(int arg0) {
        assert arg0 == cardList.size();
    }

    @Given("^A card is created$")
    public void aCardIsCreated() {
        createdCard = new Card();
    }

    @When("^I generate the numbers$")
    public void iGenerateTheNumbers() {
        createdCard.generateCard();
    }

    @Then("^The resulting card is generated properly$")
    public void theResultingCardIsGeneratedProperly() {
        int[][] numbers = createdCard.getNumbers();
        List<Integer> rownums = new ArrayList<>();
        int[] max = new int[createdCard.getCols()];
        int count = 0;
        for (int i = 0; i < createdCard.getRows(); i++) {
            for (int j = 0; j < createdCard.getCols(); j++) {
                if (numbers[i][j] == -1) {//Check three numbers missing per row
                    count++;
                    max[j]++;
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
        for (int j = 0; j < createdCard.getCols(); j++) {//Check there are at most two spaces for each column
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

        // Get createdCard
        card = (JsonPath.read(card, "$._links.createdCard.href"));
        stepDefs.result = stepDefs.mockMvc.perform(
                get(card)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
