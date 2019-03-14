package cat.udl.eps.entsoftarch.webingogeiapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason = "A problem has occurred while deleting an invitation")
public class InvitationDeleteException extends RuntimeException {

    public InvitationDeleteException() {
    }
}
