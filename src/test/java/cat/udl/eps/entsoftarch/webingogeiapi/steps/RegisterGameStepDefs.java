package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class RegisterGameStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(RegisterGameStepDefs.class);

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    GameRepository gameRepository;


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

    private ZonedDateTime startDate, finishDate;


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

    @When("^I register a new game with id \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void iRegisterANewGameWithIdAndStatus(int id, String status) throws Throwable {
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

    @And("^It has been created a game with id \"([^\"]*)\"$")
    public void itHasBeenCreatedAGame(Integer id) throws Throwable {

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has not been created a game with id \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAGameWithId(Integer id) throws Throwable {
        Assert.assertFalse(gameRepository.findById(id).isPresent());
    }

    @When("^I register a new game with id \"([^\"]*)\", pricePerCard \"([^\"]*)\", start date \"([^\"]*)\" and finish date \"([^\"]*)\" at  \"([^\"]*)\"$")
    public void iRegisterANewGameWithStartDateAndFinishDateAtPm(Integer id, double pricePerCard, String start_date, String finish_date, String finishTime) throws Throwable {
        LocalDate localDateStart = LocalDate.parse(start_date);
        LocalDate localDateFinish = LocalDate.parse(finish_date);
        LocalTime localTime = LocalTime.parse(finishTime);
        LocalTime localTimeDateStart = localTime.parse("00:00:00");

        startDate = ZonedDateTime.of(localDateStart,localTimeDateStart,ZoneId.of("Europe/Madrid"));
        finishDate = ZonedDateTime.of(localDateFinish,localTime,ZoneId.of("Europe/Madrid"));

        JSONObject game = new JSONObject();
        game.put("id", id);
        game.put("pricePerCard", pricePerCard);
        game.put("startAt",startDate);
        game.put("finishedAt",finishDate);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^A game with the id (\\d+) has been register$")
    public void aGameWithTheIdHasBeenRegister(int id) throws Throwable {

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }

    @And("^The game is registered by \"([^\"]*)\"$")
    public void theGameIsRegisteredBy(String username) throws Throwable {
        String gameJson = stepDefs.result.andReturn().getResponse().getContentAsString();
        String creatorUri = JsonPath.read(gameJson, "$._links.creator.href");
        stepDefs.result = stepDefs.mockMvc.perform(
            get(creatorUri)
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()))
            .andDo(print());
        stepDefs.result.andExpect(jsonPath("$.username", is(username)));
    }


    @And("^It has been created a game with id \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void itHasBeenEditedAGameWithId(Integer id, String status) throws Throwable {
        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.status", is(status)));
    }
}
