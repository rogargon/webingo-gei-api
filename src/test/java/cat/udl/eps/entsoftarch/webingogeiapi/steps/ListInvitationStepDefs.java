package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

public class ListInvitationStepDefs {

    private ArrayList<Invitation> list;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    private PlayerRepository playerRepo;

    @And("^The response contains one invitation with message \"([^\"]*)\"$")
    public void theResponseContainsOneInvitationWithName(String message) {

        Assert.assertEquals(1, (((Collection<?>) list).size()));

        for (Invitation inv : list)
            Assert.assertEquals(inv.getMessage(), message);
    }

    @When("^I list the invitations by user \"([^\"]*)\"$")
    public void iListTheInvitationsByUser(String user) {
        Player player = playerRepo.findByUsernameContaining(user).get(0);
        list = new ArrayList<>();
        list.addAll(invitationRepository.findByCreatedBy(player));
    }

    @Then("^The response contains two invitations$")
    public void theResponseContainsTwoInvitations() {
        Assert.assertEquals(2, (((Collection<?>) list).size()));

    }

    @When("^I list the invitations$")
    public void iListTheInvitations() {
        list = new ArrayList<>();
        list.addAll((Collection<? extends Invitation>) invitationRepository.findAll());

    }
}
