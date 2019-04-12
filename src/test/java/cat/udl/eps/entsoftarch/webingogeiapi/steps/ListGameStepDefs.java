package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ListGameStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(ListGameStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private GameRepository gameRepository;


    @When("^I list games$")
    public void iListGames() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print());
    }

    @And("^The game with id \"([^\"]*)\" is in the List response$")
    public void thePlayerWithIdIsInTheListResponse(Integer id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result.andExpect(jsonPath("$._embedded.games.*id", hasItem(id)));

    }

    @And("^The game list is empty$")
    public void thePlayersListIsEmpty() throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.games", hasSize(0)));
    }



    @When("^I list game with id \"([^\"]*)\"$")
    public void iListPlayerWithUsername(Integer id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
