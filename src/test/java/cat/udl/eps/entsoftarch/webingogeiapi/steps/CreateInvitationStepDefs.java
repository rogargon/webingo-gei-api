package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateInvitationStepDefs {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private StepDefs stepDefs;


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
    }

    @And("^Exists an invitation with message \"([^\"]*)\"$")
    public void existsAnInvitationWithMessage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
