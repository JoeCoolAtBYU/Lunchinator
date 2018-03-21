package barnett.joshua.lunchinator.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BallotReturnResults {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    BallotChoices choices;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    VotingResults results;

    public BallotReturnResults(VotingResults votingResults) {
        this.results = votingResults;
        this.choices = null;
    }

    public BallotReturnResults(BallotChoices ballotChoices) {
        this.choices = ballotChoices;
        this.results = null;
    }
}
