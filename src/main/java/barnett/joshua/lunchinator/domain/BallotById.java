package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.exception.BallotException;
import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.model.BallotChoicesModel;
import barnett.joshua.lunchinator.model.Vote;
import barnett.joshua.lunchinator.model.VoteKey;
import barnett.joshua.lunchinator.model.VoterModel;
import barnett.joshua.lunchinator.util.DateUtil;
import com.datastax.driver.mapping.annotations.Transient;
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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallotById implements Comparable<BallotById> {
    UUID ballotId;
    String endTime;
    List<Voter> voters;
    BallotChoices ballotChoices;

    @JsonIgnore
    @Transient
    Date endDate;
    @JsonIgnore
    @Transient
    Map<VoteKey, Vote> votes;

    public BallotById(BallotById ballotById) {

        this.setBallotId(ballotById.getBallotId());
        this.setEndTime(ballotById.getEndTime());
        this.setVoters(ballotById.getVoters());
        this.setBallotChoices(ballotById.getBallotChoices());
    }

    public BallotById(BallotByIdModel ballot) {

        this.setBallotId(ballot.getBallotId());
        this.setEndTime(ballot.getEndTime());
        this.setVotersFromModel(ballot.getVoters());
        this.setBallotChoicesFromModel(ballot.getBallotChoices());
        this.setVotes(ballot.getVotes());

    }

    public String returnStringId() {
        return "{" +
                "\"ballotId\":\"" + this.ballotId + "\"" +
                "}";
    }

    public void setEndTime(String endTime) {
        if (endTime == null) {
            this.endTime = DateUtil.getDefaultDateTime().toString();
        } else {
            this.endTime = endTime;
        }
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

    public void setVotersFromModel(List<VoterModel> voters) {
        if (voters == null) {
            this.voters = new ArrayList<>();
        } else {
            this.voters = voters.stream().map(voter -> new Voter(voter)).collect(Collectors.toList());
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

    public void setBallotChoicesFromModel(BallotChoicesModel ballotChoices) {
        if (ballotChoices == null) {
            this.ballotChoices = new BallotChoices();
        } else {
            this.ballotChoices = new BallotChoices(ballotChoices);
        }
    }

    public BallotChoices getBallotChoices() {
        if (this.ballotChoices == null) {
            this.ballotChoices = new BallotChoices();
        } else {
            Collections.shuffle(this.ballotChoices.getChoices());
        }
        return this.ballotChoices;
    }
}
