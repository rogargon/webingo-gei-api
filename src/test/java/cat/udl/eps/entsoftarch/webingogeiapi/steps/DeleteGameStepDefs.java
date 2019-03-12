package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteGameStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @When("^I delete a game with id \"([^\"]*)\"$")
    public void iDeleteAGameWithId(int id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^It has not been deleted a game with id \"([^\"]*)\"$")
    public void itHasNotBeenDeletedAGameWithId(int id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    @And("^It has been deleted a game with id \"([^\"]*)\"$")
    public void itHasBeenDeletedAGameWithId(int id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
