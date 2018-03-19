package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

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
}
