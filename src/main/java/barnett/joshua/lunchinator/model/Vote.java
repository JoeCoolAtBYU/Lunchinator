package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name = "vote")
public class Vote {
    @Field(name = "key")
    VoteKey voteKey;

    @Field(name = "restaurantId")
    int restaurantId;
    @Field(name = "voterName")
    String voterName;

    public Vote(int restaurantId, String voterEmail, UUID ballotId, String voterName){
        this.voteKey = new VoteKey(ballotId, voterEmail);
        this.restaurantId = restaurantId;
        this.voterName = voterName;
    }

}


