package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.Voter;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name = "voter")
public class VoterModel {

    @Field(name = "name")
    String name;

    @Field(name = "emailAddress")
    String emailAddress;

    public VoterModel(Voter voter){
        this.name = voter.getName();
        this.emailAddress = voter.getEmailAddress();
    }
}
