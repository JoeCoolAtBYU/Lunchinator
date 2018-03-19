package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name="voter")
public class VoterModel {

    @Field(name = "name")
    String name;

    @Field(name = "emailAddress")
    String emailAddress;
}
