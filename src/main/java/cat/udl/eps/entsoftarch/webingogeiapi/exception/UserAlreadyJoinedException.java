package cat.udl.eps.entsoftarch.webingogeiapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason = "Player already created a card for this game")
public class UserAlreadyJoinedException extends RuntimeException {
    public UserAlreadyJoinedException() {
    }
}
