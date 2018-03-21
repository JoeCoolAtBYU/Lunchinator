package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UDT(name = "voteKey")
public class VoteKey {

    @Field(name = "ballotId")
    UUID ballotId;
    @Field(name = "emailAddress")
    String emailAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VoteKey voteKey = (VoteKey) o;
        return Objects.equals(this.ballotId, voteKey.ballotId) &&
                Objects.equals(this.emailAddress, voteKey.emailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), this.ballotId, this.emailAddress);
    }
}
