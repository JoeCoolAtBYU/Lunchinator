package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.VoterModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voter {
    String name;
    String emailAddress;

    public Voter(Voter voter) {
        this.name = voter.getName();
        this.emailAddress = voter.getEmailAddress();
    }

    public Voter(VoterModel voter) {
        this.name = voter.getName();
        this.emailAddress = voter.getEmailAddress();
    }

}
