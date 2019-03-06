package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class ListPlayers {

    @Autowired
    private StepDefs stepDefs;

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
    public void thePlayerWithNameIsInTheResponse(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The players list is empty$")
    public void thePlayersListIsEmpty() {

    }
}
