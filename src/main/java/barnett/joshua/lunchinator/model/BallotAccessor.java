package barnett.joshua.lunchinator.model;



import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import java.util.UUID;


@Accessor
public interface BallotAccessor {
    @Query("Select * from ballotById where ballotId = ?")
    BallotByIdModel getBallotById(UUID ballotId);
}
