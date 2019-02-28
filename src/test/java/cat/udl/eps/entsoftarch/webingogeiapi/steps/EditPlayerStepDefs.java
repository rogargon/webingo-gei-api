package cat.udl.eps.entsoftarch.webingogeiapi.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class EditPlayerStepDefs {
	@Autowired
	private StepDefs stepDefs;
	private static final Logger logger = LoggerFactory.getLogger(RegisterPlayerStepDef.class);

	@When("^I edit player with username \"([^\"]*)\" a new email \"([^\"]*)\"$")
	public void iEditPlayerWithUsernameANewEmail(String username, String email) throws Throwable {
		JSONObject player = new JSONObject();
		player.put("username", username);
		player.put("email", email);
		stepDefs.result = stepDefs.mockMvc.perform(
				patch("/players/{username}", username)
						.contentType(MediaType.APPLICATION_JSON)
						.content(player.toString())
						.accept(MediaType.APPLICATION_JSON)
						.with(AuthenticationStepDefs.authenticate()))
				.andDo(print());
	}

	@And("^It has been edited a player with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
	public void itHasBeenEditedAPlayerWithUsernameAndEmailThePasswordIsNotReturned(String username, String email) throws Throwable {
		stepDefs.result = stepDefs.mockMvc
				.perform(get("/players/{username}", username).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print())
				.andExpect(jsonPath("$.email", is(email))).andExpect(jsonPath("$.password").doesNotExist());
	}

	@And("^It has not been edited a player with username \"([^\"]*)\"$")
	public void itHasNotBeenEditedAPlayerWithUsername(String username) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		stepDefs.result = stepDefs.mockMvc
				.perform(
						get("/players/{username}", username)
								.accept(MediaType.APPLICATION_JSON)
								.with(AuthenticationStepDefs.authenticate()))
				.andDo(print());

	}
}
