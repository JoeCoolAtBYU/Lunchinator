package barnett.joshua.lunchinator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BallotException extends RuntimeException {
    public BallotException(String s) {
        super(s);
    }
}
