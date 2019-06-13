package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ListInvitationStepDefs {


    @Autowired
    private StepDefs stepDefs;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    private PlayerRepository playerRepo;


    private JSONArray result;

    @And("^The response contains one invitation with message \"([^\"]*)\"$")
    public void theResponseContainsOneInvitationWithName(String message) throws JSONException {

        Assert.assertEquals(1, result.length());

        JSONObject object =  result.getJSONObject(0);
        String msg = object.getString("message");
        Assert.assertEquals(msg, message);
    }

    @When("^I list the invitations by user \"([^\"]*)\"$")
    public void iListTheInvitationsByUser(String user) throws Exception{
        Player player = playerRepo.findByUsernameContaining(user).get(0);

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/invitations/search/findByCreatedBy?createdBy={player}", player.getUri())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print());

        String invitations = stepDefs
                .result
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(invitations);
        JSONObject inv = json
                .getJSONObject("_embedded");

        result = inv
                .getJSONArray("invitations");
    }

    @When("^I list the invitations$")
    public void iListTheInvitations() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/invitations")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print());

        String invitations = stepDefs
                .result
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(invitations);
        JSONObject inv = json
                .getJSONObject("_embedded");

        result = inv
                .getJSONArray("invitations");
    }

    @Then("^The response contains \"([^\"]*)\" invitations$")
    public void theResponseContainsInvitations(String arg0) throws Throwable {
        int amount = Integer.parseInt(arg0);
        Assert.assertEquals(amount, result.length());
    }
}
