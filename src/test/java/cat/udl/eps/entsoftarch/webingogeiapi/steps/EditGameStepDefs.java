package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class EditGameStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(RegisterPlayerStepDef.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I edit game with id \"([^\"]*)\"$")
    public void iEditGameWithId(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I edit game with id \"([^\"]*)\" and set up the pricePerCard to be \"([^\"]*)\"$")
    public void iEditGameWithIdAndSetUpThePricePerCardToBe(Integer id, Double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("pricePerCard", pricePerCard);
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I edit game with id \"([^\"]*)\" and new status \"([^\"]*)\"$")
    public void iEditGameWithId(Integer id, String status) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("status", status);
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has been edited a game with id \"([^\"]*)\"$")
    public void itHasBeenEditedAGameWithId(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
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

    @And("^It has been edited a game with id \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void itHasBeenEditedAGameWithId(Integer id, String status) throws Throwable {
        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.status", is(status)));
    }

    private ZonedDateTime startDate, finishDate;

    @And("^I edit the game with id \"([^\"]*)\" and set the start date \"([^\"]*)\" at  \"([^\"]*)\"$")
    public void iEditTheGameWithIdAndSetTheStartDateAt(Integer id, String start_date, String start_hour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LocalDate localDateStart = LocalDate.parse(start_date);
        LocalTime localTime = LocalTime.parse(start_hour);

        startDate = ZonedDateTime.of(localDateStart,localTime,ZoneId.systemDefault());

        JSONObject game = new JSONObject();
        game.put("startAt",startDate);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("^I edit the game with id \"([^\"]*)\" and set the finish date \"([^\"]*)\" at \"([^\"]*)\"$")
    public void iEditTheGameWithIdAndSetTheFinishDateAt(Integer id, String finish_date, String finish_hour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LocalDate localDateFinish = LocalDate.parse(finish_date);
        LocalTime localTime = LocalTime.parse(finish_hour);

        finishDate = ZonedDateTime.of(localDateFinish,localTime,ZoneId.systemDefault());

        JSONObject game = new JSONObject();
        game.put("finishedAt",finishDate);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^I edit the game with id \"([^\"]*)\" and set the jackpot amount to be \"([^\"]*)\"$")
    public void iEditTheGameWithIdAndSetTheJackpotAmountToBe(Integer id, String jackpot) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject game = new JSONObject();
        game.put("jackpot", jackpot );
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(game.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("^The game with id \"([^\"]*)\" has the start date \"([^\"]*)\" at  \"([^\"]*)\"$")
    public void theGameWithIdHasTheStartDateAt(Integer id, String start_date, String start_hour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LocalDate localDateStart = LocalDate.parse(start_date);
        LocalTime localTime = LocalTime.parse(start_hour);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;


        startDate = ZonedDateTime.of(localDateStart,localTime,ZoneId.systemDefault());
        String startDate1 = startDate.format(formatter);
        ZonedDateTime parsedStartDate = ZonedDateTime.parse(startDate1, formatter);

        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.startAt",is(parsedStartDate.toString())));
    }


    @And("^The game with id \"([^\"]*)\" has the finish date \"([^\"]*)\" at \"([^\"]*)\"$")
    public void theGameWithIdHasTheFinishDateAt(Integer id, String finish_date, String finish_hour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        LocalDate localDateFinish = LocalDate.parse(finish_date);
        LocalTime localTime = LocalTime.parse(finish_hour);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        finishDate = ZonedDateTime.of(localDateFinish,localTime,ZoneId.systemDefault());

        String finishDate1 = finishDate.format(formatter);
        ZonedDateTime parsedFinishDate = ZonedDateTime.parse(finishDate1, formatter);

        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.finishedAt",is(parsedFinishDate.toString())));

    }

    @And("^The game with id \"([^\"]*)\" has the jackpot amount \"([^\"]*)\"$")
    public void theGameWithIdHasTheJackpotAmount(Integer id, Double jackpot) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc
                .perform(get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.jackpot", is(jackpot)));
    }
}
