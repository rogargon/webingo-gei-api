package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class CreateInvitationStepDefs {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private StepDefs stepDefs;

    private String url;

    @And("^There is an user \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void thereIsAnUserWithPassword(String arg0, String arg1) throws Throwable {
        Player player = new Player();
        playerRepo.save(player);
    }

    @When("^I create an invitation with message \"([^\"]*)\"$")
    public void iCreateAnInvitationWithMessage(String message) throws Throwable {
        JSONObject player = new JSONObject();
        player.put("message", message);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        url = stepDefs.result.andReturn().getResponse().getHeader("location");
    }

    @And("^Exists an invitation with message \"([^\"]*)\"$")
    public void existsAnInvitationWithMessage(String text) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(url).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print()).andExpect(jsonPath("$.message", is(text)));
    }
}
