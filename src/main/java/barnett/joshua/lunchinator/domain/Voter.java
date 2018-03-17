package barnett.joshua.lunchinator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voter {
    String name;
    String emailAddress;

    public Voter(Voter voter){
        this.name = voter.getName();
        this.emailAddress = voter.getEmailAddress();
    }

}
