package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ballot {
    List<Voter> voters;
    String endTime;

    @JsonIgnore
    Date endDate;
    UUID ballotId;

    public Ballot(Ballot ballot) {

        if (ballot.getEndTime() == null) {
            this.endTime = DateUtil.getDefaultDateTime().toString();
            setEndDate(endTime);
        } else {
            this.endTime = ballot.getEndTime();
            setEndDate(endTime);
        }

        if (ballot.getVoters() == null) {
            this.voters = new ArrayList();
        } else {
            this.voters = ballot.voters;
        }


        this.ballotId = UUID.randomUUID();
    }

    public String returnStringId(){
        return "{" +
                  "\"ballotId\":\"" + ballotId + "\"" +
                "}";
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;

        setEndDate(endTime);
    }

    private void setEndDate(String endTime) {
        try {
            //"3/20/17 11:45"
            this.endDate = new SimpleDateFormat("M/dd/yy HH:mm").parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace(); //TODO handle error better.
        }
    }

}
