package barnett.joshua.lunchinator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class VoteTimePassedException extends RuntimeException{

        private static final long serialVersionUID = -8942018993792368679L;

        public VoteTimePassedException() {
            super("The voting time for this ballot has expired");
        }

}
