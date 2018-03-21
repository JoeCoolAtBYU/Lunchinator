package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.domain.BallotChoices;
import barnett.joshua.lunchinator.exception.VoteTimePassedException;
import barnett.joshua.lunchinator.service.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class LunchinatorController {

    @Autowired
    BallotService ballotService;

    @RequestMapping(value = "/create-ballot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createBallot(@RequestBody BallotById ballotById) {
        BallotById returnBallotById = this.ballotService.getBallot(ballotById);

        return new ResponseEntity<>(returnBallotById.returnStringId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ballot/{ballotId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BallotChoices> getBallot(@PathVariable UUID ballotId) {
        BallotById ballot = this.ballotService.getBallot(ballotId);
        if (!ballot.getEndDate().before(new Date())) {
            return new ResponseEntity<>(ballot.getBallotChoices(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    public void vote(@RequestParam int id, @RequestParam UUID ballotId, @RequestParam String voterName, @RequestParam String emailAddress) {
        BallotById ballot = this.ballotService.getBallot(ballotId);
        if (!ballot.getEndDate().before(new Date())) {
            this.ballotService.vote(id, ballotId, voterName, emailAddress);
        } else {
            throw new VoteTimePassedException();
        }
    }
}
