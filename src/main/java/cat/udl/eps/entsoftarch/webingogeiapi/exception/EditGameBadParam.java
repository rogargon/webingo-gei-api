package cat.udl.eps.entsoftarch.webingogeiapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Bad Param")
public class EditGameBadParam extends RuntimeException {

    public EditGameBadParam() {
    }
}
