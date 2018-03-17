package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Ballot {
    List voters;
    Date endTime;
    UUID ballotId;

    public Ballot(Ballot ballot){

        if (ballot.getEndTime() == null){
            this.endTime = DateUtil.getDefaultDateTime();
        } else {
            this.endTime = ballot.getEndTime();
        }

        if(ballot.getVoters() == null){
            this.voters = new ArrayList();
        } else {
            this.voters = ballot.voters;
        }


        this.ballotId = UUID.randomUUID();
    }

}
