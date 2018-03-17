package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.Ballot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LunchinatorController {

    @RequestMapping(value = "/create-ballot", method = RequestMethod.POST)
    public UUID createBallot(@RequestBody Ballot ballot) {
        return new Ballot(ballot).getBallotId();
    }
}
