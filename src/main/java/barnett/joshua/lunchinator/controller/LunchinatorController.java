package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.domain.BallotChoices;
import barnett.joshua.lunchinator.service.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<String> createBallot(@RequestBody BallotById ballotById) {
        BallotById returnBallotById = this.ballotService.getBallot(ballotById);

        return new ResponseEntity<String>(returnBallotById.returnStringId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ballot/{ballotId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BallotChoices> getBallot(@PathVariable UUID ballotId) {
        return new ResponseEntity<BallotChoices>(this.ballotService.getBallot(ballotId).getBallotChoices(), HttpStatus.OK);
    }
}
