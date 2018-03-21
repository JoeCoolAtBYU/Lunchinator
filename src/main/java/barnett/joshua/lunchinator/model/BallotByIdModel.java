package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.BallotById;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Table(keyspace = "lunch", name = "ballotById", readConsistency = "LOCAL_QUORUM", writeConsistency = "LOCAL_QUORUM")
public class BallotByIdModel {

    @Column(name = "ballotId")
    UUID ballotId;

    @Column(name = "endTime")
    String endTime;

    @Column(name = "voters")
    List<VoterModel> voters;

    @Column(name = "ballotChoices")
    BallotChoicesModel ballotChoices;

    @Column(name = "votes")
    Map<VoteKey, Vote> votes;

    public BallotByIdModel(BallotById ballotById) {
        if (ballotById.getBallotId() == null) {
            this.ballotId = UUID.randomUUID();
        } else {
            this.ballotId = ballotById.getBallotId();
        }
        this.endTime = ballotById.getEndTime();
        this.voters = ballotById.getVoters().stream().map(voter -> new VoterModel(voter)).collect(Collectors.toList());
        this.setBallotChoices(new BallotChoicesModel(ballotById.getBallotChoices()));
    }

    public BallotChoicesModel getBallotChoices() {
        Collections.shuffle(this.ballotChoices.getChoices());
        return this.ballotChoices;
    }

    public void setBallotChoices(BallotChoicesModel ballotChoices){
        if (ballotChoices == null){
            this.ballotChoices = new BallotChoicesModel();
        } else {
            this.ballotChoices = ballotChoices;
        }
    }

    public void setVotes(VoteKey key, Vote vote) {
        if (this.votes == null){
            this.votes = new HashMap<>();
        }

        this.votes.put(key, vote);
    }
}
