package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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


import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CardStepDef {
    @Autowired
    CardRepository cr;
    @Autowired
    GameRepository gr;
    String idc;

    private final StepDefs stepDefs;

    public CardStepDef(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @Given("^There is a game with price (\\d+.\\d+) and id (\\d+)$")
    public void thereIsAGame(double pricePerCard, int id) throws Exception {
        Game g = new Game();
        g.setId(id);
        g.setPricePerCard(pricePerCard);
        gr.save(g);
    }

    @When("^I join the Game with id (\\d+)$")
    public void iJoinThePreviouslyGame(int arg) throws Exception {
        Card c = new Card();
        if(gr.findById(arg).isPresent()){
            c.setGame(gr.findById(arg).get());
        }else{
            assert false;
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
        cr.save(c);
    }

    @Then("^A card has been created with price (\\d+.\\d+) for the game with id (\\d+)$")
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
                .andDo(print()).andExpect(jsonPath("$._links.self.href", endsWith("/games/" + String.valueOf(arg2))));

    }

    @And("^I set up the pricePerCard to be \"([^\"]*)\"$")
    public void iSetUpThePricePerCardToBe(Double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //do a get and print to probe
        throw new PendingException();
    }
}
