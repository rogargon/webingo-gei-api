package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;

public class ListGameStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(ListGameStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private GameRepository gameRepository;

    private JSONArray result;

    @When("^I list the previous games$")
    public void iListGames() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print());

        String games = stepDefs
                .result
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(games);
        JSONObject game = json
                .getJSONObject("_embedded");

        result = game
                .getJSONArray("games");
    }

    @And("^The list contains \"([^\"]*)\" games$")
    public void theListContainsGames(String arg0) throws Throwable {
        int amount = Integer.parseInt(arg0);
        Assert.assertEquals(amount, result.length());
    }
}
