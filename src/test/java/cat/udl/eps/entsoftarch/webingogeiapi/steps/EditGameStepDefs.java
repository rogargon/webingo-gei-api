package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class EditGameStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(RegisterPlayerStepDef.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I try to edit a new game with id \"([^\"]*)\"$")
    public void iEditAGameWithId(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Edit enabled at the game with id \"([^\"]*)\"$")
    public void itHasBeenEnabledGameEdit(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Edit not enabled at the game with id \"([^\"]*)\"$")
    public void itHasBeenNotEnabledGameEdit(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I edit game with id \"([^\"]*)\"$")
    public void iEditGameWithId(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has been edited a game with id \"([^\"]*)\"$")
    public void itHasBeenEditedAGameWithId(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has not been edited a game with id \"([^\"]*)\"$")
    public void itHasNotBeenEditedAGameWithId(int id) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Exists an admin \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void existsAnAdminWithPassword(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I want to login in order to edit a game$")
    public void iWantToLoginInOrderToEditAGame() {
        
    }

    @And("^I login with admin \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginWithAdminAndPassword(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Exists a game with id \"([^\"]*)\"$")
    public void existsAGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I set up the jackpot to be \"([^\"]*)\"$")
    public void iSetUpTheJackpotToBe(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has been edited the jackpot for the game with id \"([^\"]*)\"$")
    public void itHasBeenEditedTheJackpotForTheGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I set up the starting time startAt to be \"([^\"]*)\"$")
    public void iSetUpTheStartingTimeStartAtToBe(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has been edited the starting time startAt for the game with id \"([^\"]*)\"$")
    public void itHasBeenEditedTheStartingTimeStartAtForTheGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I set up the finishing time finishedAt to be \"([^\"]*)\"$")
    public void iSetUpTheFinishingTimeFinishedAtToBe(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has been edited the finishing time finishedAt for the game with id \"([^\"]*)\"$")
    public void itHasBeenEditedTheFinishingTimeFinishedAtForTheGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I register a new game with id \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void iRegisterANewGameWithIdAndStatus(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I register a new game with id \"([^\"]*)\"$")
    public void iRegisterANewGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I set up the pricePerCard to be \"([^\"]*)\"$")
    public void iSetUpThePricePerCardToBe(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^It has been edited the price PerCard for the game with id \"([^\"]*)\"$")
    public void itHasBeenEditedThePricePerCardForTheGameWithId(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
