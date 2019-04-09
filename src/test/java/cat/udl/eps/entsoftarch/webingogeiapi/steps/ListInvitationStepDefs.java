package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.AdminRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.UserRepository;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ListInvitationStepDefs {

    private ArrayList<Invitation> list;

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private UserRepository userRepository;

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
        list = new ArrayList<>();

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

    @Then("^The response contains two invitations$")
    public void theResponseContainsTwoInvitations() {
        Assert.assertEquals(2, result.length());

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
}
