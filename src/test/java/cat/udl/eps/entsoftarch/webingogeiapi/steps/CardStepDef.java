package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.WebingoGeiApiApplication;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(
        classes = {WebingoGeiApiApplication.class},
        loader = SpringBootContextLoader.class
)
@DirtiesContext
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class CardStepDef {
    @When("^I create a new game with price (\\\\d+.\\\\d+)$")
    public void I_create_a_new_game_with_price(double arg) {

    }

    @And("^I create a card$")
    public void iCreateACard() {
    }

    @And("^A card has been created with price (\\\\d+.\\\\d+)")
    public void aCardHasBeenCreatedWithPrice(int arg0, int arg1) {
    }

    @Given("^A card is created$")
    public void aCardIsCreated() {
        Card card = new Card();
        System.out.println(card.toString());
        int a=0;
    }
}
