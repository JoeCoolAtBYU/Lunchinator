package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.domain.VotingResults;
import barnett.joshua.lunchinator.exception.BallotException;
import barnett.joshua.lunchinator.exception.VoteTimePassedException;
import barnett.joshua.lunchinator.repo.Repo;
import barnett.joshua.lunchinator.service.BallotService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @Autowired
    Repo repo;

    @RequestMapping(value = "/create-ballot", method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 409, message = "You will get this if the expiration date is already in the past.")})
    @ApiOperation(value = "creates a new ballot")
    public ResponseEntity<String> createBallot(@RequestBody BallotById ballotById) {
        ballotById.setEndDate(ballotById.getEndTime());
        if (!ballotById.getEndDate().before(new Date())) {
            BallotById returnBallotById = this.ballotService.getBallot(ballotById);
            return new ResponseEntity<>(returnBallotById.returnStringId(), HttpStatus.OK);
        } else {
            throw new BallotException("Ballot Expirey date already in the past");
        }

    }

    @RequestMapping(value = "/ballot/{ballotId}", method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 409, message = "You will get this if the expiration date is already in the past.")})
    @ApiOperation(value = "This endpoint will return the Current choices of restaurants to choose from if the date and time is before the expiration date of the balllot" +
            " Or will will return the results of the voting if the date and time is after the expirtation date of the ballot.")
    public ResponseEntity<?> getBallot(@PathVariable UUID ballotId) {
        BallotById ballot = this.ballotService.getBallot(ballotId);
        if (!ballot.getEndDate().before(new Date())) {
            return ResponseEntity.ok(ballot.getBallotChoices());
        } else {
            return ResponseEntity.ok(new VotingResults(ballot, this.repo));
        }
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 409, message = "You will get this if the expiration date is already in the past.")})
    @ApiOperation(value = "Adds votes for each restaurant on the specified ballot.")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "RestaurantId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "ballotId", value = "The Id for the ballot wanting to be voted on", required = true, dataType = "UUID"),
            @ApiImplicitParam(name = "voterName", value = "name of the person Voting", required = true, dataType = "string"),
            @ApiImplicitParam(name = "emailAddress", value = "email of the person Voting", required = true, dataType = "string")
    })
    public void vote(@RequestParam int id, @RequestParam UUID ballotId, @RequestParam String voterName, @RequestParam String emailAddress) {
        BallotById ballot = this.ballotService.getBallot(ballotId);
        if (!ballot.getEndDate().before(new Date())) {
            this.ballotService.vote(id, ballotId, voterName, emailAddress);
        } else {
            throw new VoteTimePassedException();
        }
    }
}
