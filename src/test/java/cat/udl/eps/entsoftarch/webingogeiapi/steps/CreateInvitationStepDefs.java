package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class CreateInvitationStepDefs {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private InvitationRepository invitationRepo;

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
                get(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.message", is(text)));
    }

    @And("^And it doesn't exist an invitation with message \"([^\"]*)\"$")
    public void andItDoesnTExistAnInvitationWithMessage(String text) throws Throwable {
        Assert.assertEquals(0, invitationRepo.count());
        Assert.assertEquals(0, invitationRepo.findByMessageContaining(text).size());
    }

    @When("^I create an invitation with no message$")
    public void iCreateAnInvitationWithNoMessage() throws Throwable{
        JSONObject player = new JSONObject();
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^And it exists \"([^\"]*)\" invitations$")
    public void andItExistsInvitations(String arg0) throws Throwable {
        Assert.assertEquals(0, invitationRepo.count());
    }

    @When("^I create an invitation with a \"([^\"]*)\" chars message$")
    public void iCreateAnInvitationWithACharsMessage(String arg0) throws Throwable {
        char[] charArray = new char[Integer.parseInt(arg0)];
        Arrays.fill(charArray, ' ');
        String str = new String(charArray);


        JSONObject player = new JSONObject();
        player.put("", str);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
