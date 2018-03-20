package barnett.joshua.lunchinator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BallotNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -8942018993792368679L;

    public BallotNotFoundException(UUID ballotId) {
        super("There are no Ballots with the id of: " + ballotId);
    }
}

