package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.Ballot;
import barnett.joshua.lunchinator.service.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class LunchinatorController {

    @Autowired
    BallotService ballotService;

    @RequestMapping(value = "/create-ballot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createBallot(@RequestBody Ballot ballot) {
        Ballot ballotId = new Ballot(ballot);
        return new ResponseEntity<String>(ballotId.returnStringId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-ballot/{ballotId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Ballot> getBallot(UUID ballotId) {
        return new ResponseEntity<Ballot>(this.ballotService.getBallot(ballotId), HttpStatus.OK);
    }
}
