package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

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
    private String idc;
    private Exception actualException;


    private final StepDefs stepDefs;

    public CardStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @Given("^There is a game with price (.+) and id (\\d+)$")
    public void thereIsAGame(double arg, int arg2) {
        Game g = new Game();
        g.setId(arg2);
        g.setPricePerCard(arg);
        try {
            gr.save(g);
        }catch (Exception e){
            actualException = e;
        }
    }

    @When("^I join the Game with id (\\d+)$")
    public void iJoinThePreviouslyGame(int arg) throws Exception {
        Card c = new Card();
        if(gr.findById(arg).isPresent()){
            c.setGame(gr.findById(arg).get());
        }else{
            throw new Exception("The game with id " + arg +" does not exist");
        }
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
    }

    @Then("^A card has been created with price (.+) for the game with id (\\d+)$")
    public void CardCreated(double arg, int arg2) throws Exception{
        Assert.assertNotNull("Location not null", idc);
        stepDefs.result = stepDefs.mockMvc.perform(
                get(idc)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        String game = stepDefs.result.andReturn().getResponse().getContentAsString();
        String a = (JsonPath.read(game, "$._links.game.href"));
        stepDefs.result = stepDefs.mockMvc.perform(
                get(a)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(jsonPath("$._links.self.href", endsWith("/games/" + arg2)));

    }

    @Then("^A \"([^\"]*)\" occurs$")
    public void aOccurs(String arg0) {
        Assert.assertEquals("Exception MissMatch", arg0,actualException.getClass().getSimpleName());
    }

    @And("^There is a card with id (\\d+) associated to the game with id (\\d+)$")
    public void thereIsACardWithIdAssociatedToTheGameWithId(int arg0, int arg1) throws Exception {
        Card c = new Card();
        c.setId(arg0);
        if(gr.findById(arg1).isPresent()){
            c.setGame(gr.findById(arg1).get());
        }
        String json = stepDefs.mapper.writeValueAsString(c);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        //cr.save(c);
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
                .andExpect(status().is2xxSuccessful());
    }
}
