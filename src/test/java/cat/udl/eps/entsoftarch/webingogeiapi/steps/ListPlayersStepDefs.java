package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class ListPlayersStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository UserRepository;



    @When("^I list players$")
    public void iListPlayers() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print());

    }

    @And("^The player with name \"([^\"]*)\" is in the response$")
    public void thePlayerWithNameIsInTheResponse(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result.andExpect(jsonPath("$._embedded.players.*.username", hasItem(username)));

    }


    @And("^The players list is empty$")
    public void thePlayersListIsEmpty() throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.players", hasSize(1)));
    }
}
