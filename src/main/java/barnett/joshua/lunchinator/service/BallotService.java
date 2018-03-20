package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BallotService {
    @Autowired
    Repo repo;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantReviewService restaurantReviewService;

    public BallotById getBallot(UUID ballotId){
        BallotByIdModel ballotById = repo.getBallot(ballotId);
        if (ballotById.getBallotChoices() == null){
            restaurantService.getAllRestaurants();
            restaurantReviewService.getAllReviews();
            restaurantService.getFiveRestaurants(ballotById);
            this.repo.saveBallot(ballotById);
        }
        return new BallotById(ballotById, ballotById.getVoters());
    }

    public BallotById getBallot(BallotById ballotById){
        BallotByIdModel ballotId = this.repo.getBallot(this.repo.saveBallot(ballotById));
        return new BallotById(ballotId, ballotId.getVoters());
    }

//    public BallotChoicesModel getBallotChoices(UUID ballotId) {
//        BallotById ballotById = getBallot(ballotId);
//        this.repo.saveBallot(ballotById);
//        return ballotById.getBallotChoices();
//    }
}
