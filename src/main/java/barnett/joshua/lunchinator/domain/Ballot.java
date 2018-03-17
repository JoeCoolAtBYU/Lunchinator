package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Ballot {
    //Voter[] voters;
    Date endTime;
    UUID ballotId;

    public Ballot(Ballot ballot){

        if (ballot.getEndTime() == null){
            this.endTime = DateUtil.getDefaultDateTime();
        } else {
            this.endTime = ballot.getEndTime();
        }

        this.ballotId = UUID.randomUUID();
    }

}
