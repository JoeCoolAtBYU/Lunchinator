package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UDT(keyspace = "lunch", name = "vote")
public class Vote {
    @PartitionKey(0)
    UUID ballotId;

    @PartitionKey(1)
    String eamilAddress;

    int restaurantId;
    String voterName;


}


