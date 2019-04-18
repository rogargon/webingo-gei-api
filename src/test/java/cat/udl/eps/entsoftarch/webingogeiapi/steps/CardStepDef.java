package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CardStepDef {
    @Autowired
    CardRepository cr;
    @Autowired
    GameRepository gr;
    String idc;

    private final StepDefs stepDefs;

    public CardStepDef(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @When("^I join the Game with id (\\d+)$")
    public void iJoinThePreviouslyGame(int arg) throws Exception {
        Card c = new Card();
        if(gr.findById(arg).isPresent()){
            c.setGame(gr.findById(arg).get());
        }else{
            assert false;
        }
        String json = stepDefs.mapper.writeValueAsString(c);
        System.out.println("JSON " + json);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
        idc = stepDefs.result.andReturn().getResponse().getHeader("Location");
        cr.save(c);
    }

    @And("^I set up the pricePerCard to be \"([^\"]*)\"$")
    public void iSetUpThePricePerCardToBe(Double pricePerCard) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //do a get and print to probe
        throw new PendingException();
    }



}
