package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.exception.BallotException;
import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.model.VoterModel;
import barnett.joshua.lunchinator.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallotById implements Comparable<BallotById> {
    List<Voter> voters;
    String endTime;

    @JsonIgnore
    Date endDate;
    UUID ballotId;
    BallotChoices ballotChoices;

    public BallotById(BallotById ballotById) {

        if (ballotById.getEndTime() == null) {
            this.endTime = DateUtil.getDefaultDateTime().toString();
            setEndDate(endTime);
        } else {
            this.endTime = ballotById.getEndTime();
            setEndDate(endTime);
        }

        if (ballotById.getVoters() == null) {
            this.voters = new ArrayList();
        } else {
            this.voters = ballotById.voters;
        }

        if (ballotById.getBallotId() == null) {
            this.ballotId = UUID.randomUUID();
        } else {
            this.ballotId = ballotById.getBallotId();
        }

        if (ballotById.getBallotChoices() == null) {
            this.ballotChoices = new BallotChoices();
        } else {
            this.ballotChoices = ballotById.getBallotChoices();
        }
    }

    public BallotById(BallotByIdModel ballot, List<VoterModel> voters) {

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

        if (ballot.getBallotChoices() == null) {

            this.ballotChoices = new BallotChoices();
        } else {
            this.ballotChoices = new BallotChoices(ballot.getBallotChoices());
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
    public int compareTo(BallotById o) {
        if (this.endDate.before(o.endDate)) {
            return 1;
        } else if (this.endDate.after(o.endDate)) {
            return -1;
        } else {
            return 0;
        }
    }

    public void setVoters(List<Voter> voters) {
        if (voters == null) {
            this.voters = new ArrayList<>();
        } else {
            this.voters = voters;
        }
    }

    public void setEndDate(Date endDate) {
        if (endDate == null) {
            this.endDate = DateUtil.getDefaultDateTime();
        } else {
            this.endDate = endDate;
        }
    }

    public void setBallotId(UUID ballotId) {
        if (ballotId == null) {
            throw new BallotException("BallotById Id Cannot be null.");
        } else {
            this.ballotId = ballotId;
        }
    }

    public void setBallotChoices(BallotChoices ballotChoices) {
        if (ballotChoices == null) {
            this.ballotChoices = new BallotChoices();
        } else {
            this.ballotChoices = ballotChoices;
        }
    }

    public BallotChoices getBallotChoices() {
        Collections.shuffle(ballotChoices.getChoices());
        return ballotChoices;
    }
}
