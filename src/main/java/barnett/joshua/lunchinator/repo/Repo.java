package barnett.joshua.lunchinator.repo;

import barnett.joshua.lunchinator.Application;
import barnett.joshua.lunchinator.domain.Ballot;
import barnett.joshua.lunchinator.model.BallotAccessor;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("cassandra")
public class Repo {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    Session session;

    private MappingManager mappingManager;
    private BallotAccessor ballotAccessor;

    @Autowired
    public Repo(Session session) {
        this.mappingManager = new MappingManager(session);
        this.ballotAccessor = this.mappingManager.createAccessor(BallotAccessor.class);

    }

    public Ballot getBallotById(UUID ballotId) {
        return this.ballotAccessor.getBallotById(ballotId);
    }


}
