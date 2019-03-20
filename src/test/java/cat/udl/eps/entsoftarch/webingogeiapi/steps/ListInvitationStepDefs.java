package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class ListInvitationStepDefs {

    private Iterable<Invitation> list;

    @Autowired
    InvitationRepository invitationRepository;

    @When("^I list the invitations$")
    public void iListTheInvitations() {
        list = invitationRepository.findAll();
    }

    @And("^The response contains one invitation with message \"([^\"]*)\"$")
    public void theResponseContainsOneInvitationWithName(String message) {

        assert (((Collection<?>) list).size() == 1);

        for (Invitation inv : list)
            assert (inv.getMessage().equals(message));
    }
}
