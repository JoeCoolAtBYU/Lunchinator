package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.BallotModel;
import barnett.joshua.lunchinator.model.VoterModel;
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
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ballot implements Comparable<Ballot> {
    List<Voter> voters;
    String endTime;

    @JsonIgnore
    Date endDate;
    UUID ballotId;
    BallotChoices ballotChoices;

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

    public Ballot(BallotModel ballot, List<VoterModel> voters) {

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
            this.voters = voters.stream().map(voter -> new Voter(voter)).collect(Collectors.toList());
        }

        if (ballot.getBallotId() == null) {
            this.ballotId = UUID.randomUUID();
        } else {
            this.ballotId = ballot.getBallotId();
        }
    }

    public String returnStringId() {
        return "{" +
                "\"ballotId\":\"" + ballotId + "\"" +
                "}";
    }

    public void setEndTime(String endTime) {
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

    @Override
    public int compareTo(Ballot o) {
        if (this.endDate.before(o.endDate)) {
            return 1;
        } else if (this.endDate.after(o.endDate)) {
            return -1;
        } else {
            return 0;
        }
    }
}
