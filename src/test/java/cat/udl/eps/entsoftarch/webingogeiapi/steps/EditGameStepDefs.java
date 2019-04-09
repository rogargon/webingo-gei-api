package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class EditGameStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(RegisterPlayerStepDef.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I edit game with id \"([^\"]*)\"$")
    public void iEditGameWithId(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I edit game with id \"([^\"]*)\" and new status \"([^\"]*)\"$")
    public void iEditGameWithId(int id, String status) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("status", status);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has been edited a game with id \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void itHasBeenEditedAGameWithId(int id, String status) throws Throwable {
        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.status", is(status)));
    }

    @And("^It has not been edited a game with id \"([^\"]*)\"$")
    public void itHasNotBeenEditedAGameWithId(int id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc
                .perform(
                        get("/games/{id}", id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I edit game with id \"([^\"]*)\" and set up the pricePerCard to be \"([^\"]*)\"$")
    public void iEditGameWithIdAndSetUpThePricePerCardToBe(int id, Double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("pricePerCard", pricePerCard);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}