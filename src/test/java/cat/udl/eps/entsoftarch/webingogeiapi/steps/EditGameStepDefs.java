package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class EditGameStepDefs {

    @Autowired
    private final StepDefs stepDefs;
    GameRepository gameRepository;
    String idc;

    public EditGameStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @Given("^There is a game with the pricePerCard (\\d+.\\d+) and id (\\d+)$")
    public void thereIsAGameWithThePricePerCardAndId(double pricePerCard, int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("pricePerCard",pricePerCard);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
            }


    @When("^I edit the game with id (\\d+)$")
    public void iEditTheGameWithId(int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("id", id);
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());


    }

    @And("^I edit the pricePerCard to be (\\d+.\\d+) for the game with id (\\d+)$")
    public void iEditThePricePerCardToBeForTheGameWithId(int newpricePerCard, int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("pricePerCard",newpricePerCard);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The pricePerCard has been edited to be (\\d+.\\d+) for the game with id (\\d+)$")
    public void thePricePerCardHasBeenEditedToBeForTheGameWithId(int pricePerCard, int id) throws Exception {
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
                .andDo(print()).andExpect(jsonPath("$._links.self.href", endsWith("/games/" + String.valueOf(id))));

    }

    @And("^The game with id (\\d+) has been edited$")
    public void theGameWithIdHasBeenEdited(int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("id",id);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Given("^Exists an admin \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void existsAnAdminWithPassword(String admin, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("admin", admin);
        game.put("password", password);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("^There is a game with id (\\d+)$")
    public void thereIsAGameWithId(int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("id",id);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
}


    @And("^I edit the jackpot to be (\\d+.\\d+) for the game with id (\\d+)$")
    public void iEditTheJackpotToBeForTheGameWithId(float jackpot, int id) throws Exception {
        JSONObject game = new JSONObject();
        game.put("jackpot",jackpot);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}",id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(game.toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("^I edit the starting time startAt to be \"([^\"]*)\" for the game with id (\\d+)$")
    public void iEditTheStartingTimeStartAtToBeForTheGameWithId(String startAt, int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        ZonedDateTime startAtTime = ZonedDateTime.parse(startAt);
        game.put("startAt",startAtTime);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

}
