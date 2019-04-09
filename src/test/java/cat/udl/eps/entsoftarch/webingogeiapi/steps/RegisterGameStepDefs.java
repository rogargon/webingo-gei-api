package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.GameStatus;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


public class RegisterGameStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(RegisterGameStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I register a new game with id \"([^\"]*)\" and pricePerCard \"([^\"]*)\"$")
    public void iRegisterANewGameWithId(Integer id, double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("id", id);
        game.put("pricePerCard", pricePerCard);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I register a new game with id \"([^\"]*)\"$")
    public void iRegisterANewGame(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("id", id);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("^It has been created a game with id \"([^\"]*)\"$")
    public void itHasBeenCreatedAGame(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has not been created a game with id \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAGameWithId(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @When("^I register a new game with id \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void iRegisterANewGameWithIdAndStatus(int id, String status) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("id", id);
        game.put("status", status);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
