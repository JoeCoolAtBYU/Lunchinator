package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.Ballot;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Table(keyspace = "lunch", name = "ballotById", readConsistency = "LOCAL_QUORUM", writeConsistency = "LOCAL_QUORUM")
public class BallotModel {

    @Column(name = "ballotId")
    UUID ballotId;

    @Column(name = "endTime")
    String endTime;

    @Column(name = "voters")
    List<VoterModel> voters;

    public BallotModel(Ballot ballot) {
        if (ballot.getBallotId() == null) {
            this.ballotId = UUID.randomUUID();
        } else {
            this.ballotId = ballot.getBallotId();
        }
        this.endTime = ballot.getEndTime();
        this.voters = ballot.getVoters().stream().map(voter -> new VoterModel(voter)).collect(Collectors.toList());
    }
}
