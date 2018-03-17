package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.Ballot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LunchinatorController {

    @RequestMapping(value = "/create-ballot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createBallot(@RequestBody Ballot ballot) {
        Ballot ballotId = new Ballot(ballot);
        return new ResponseEntity<String>(ballotId.returnStringId(), HttpStatus.OK);
    }
}
