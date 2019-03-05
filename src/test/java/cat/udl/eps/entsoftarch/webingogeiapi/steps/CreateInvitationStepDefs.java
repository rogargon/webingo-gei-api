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

    @When("^I send an invitation to \"([^\"]*)\"$")
    public void iSendAnInvitationTo(String username) throws Throwable {
        Player player = playerRepo.findById(username).orElse(null);


    }

    @When("^I send an invitation with message \"([^\"]*)\"$")
    public void iSendAnInvitationWithMessage(String message) throws Throwable {
        Player player = playerRepo.findById(username).orElse(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
