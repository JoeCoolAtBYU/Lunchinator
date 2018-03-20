package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.BallotById;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
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

    public BallotByIdModel(BallotById ballotById) {
        if (ballotById.getBallotId() == null) {
            this.ballotId = UUID.randomUUID();
        } else {
            this.ballotId = ballotById.getBallotId();
        }
        this.endTime = ballotById.getEndTime();
        this.voters = ballotById.getVoters().stream().map(voter -> new VoterModel(voter)).collect(Collectors.toList());
    }

    public BallotChoicesModel getBallotChoices() {
        Collections.shuffle(ballotChoices.getChoices());
        return ballotChoices;
    }
}
