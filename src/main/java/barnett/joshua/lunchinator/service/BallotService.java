package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.domain.Ballot;
import barnett.joshua.lunchinator.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BallotService {
    @Autowired
    Repo repo;

    public Ballot getBallot(UUID ballotId){
        return repo.getBallotById(ballotId);
    }
}
