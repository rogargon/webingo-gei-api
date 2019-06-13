package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CardStepDef {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    GameRepository gameRepository;
    String idCard;

    private final StepDefs stepDefs;

    public CardStepDef(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @When("^I join the Game with id (\\d+)$")
    public void iJoinThePreviouslyGame(int arg) throws Exception {
        Card card = new Card();
        if(gameRepository.findById(arg).isPresent()){
            card.setGame(gameRepository.findById(arg).get());
        }else{
            assert false;
        }
        String json = stepDefs.mapper.writeValueAsString(card);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
        idCard = stepDefs.result.andReturn().getResponse().getHeader("Location");
        cardRepository.save(card);
    }

    @And("^I set up the pricePerCard to be \"([^\"]*)\"$")
    public void iSetUpThePricePerCardToBe(Double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //do a get and print to probe
        throw new PendingException();
    }



}
