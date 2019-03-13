package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteInvitationStepDefs {

    @Autowired
    StepDefs stepDefs;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    PlayerRepository playerRepository;

    private String url;

    private long id;


    @And("^There are (\\d+) invitations created$")
    public void thereAreInvitationsCreated(int numInvitations) {
        long invitationsInRepository = invitationRepository.count();
        Assert.assertEquals(numInvitations, invitationsInRepository);

    }

    @And("^There is an invitation with message \"([^\"]*)\"$")
    public void thereIsAnInvitationWithMessage(String message) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        Invitation inv = new Invitation();
        inv.setMessage(message);
        invitationRepository.save(inv);
        id = inv.getId();

    }

    @When("^I delete the previously created invitation$")
    public void iDeleteThePreviouslyCreatedInvitation() throws Throwable {

        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/invitations/{id}", id)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

}
