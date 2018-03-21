package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.model.Vote;
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
        BallotByIdModel ballotById = this.repo.getBallot(ballotId);
        if (ballotById.getBallotChoices() == null || ballotById.getBallotChoices().getSuggestion().getId() == -1){
            this.restaurantService.getAllRestaurants();
            this.restaurantReviewService.getAllReviews();
            this.restaurantService.getFiveRestaurants(ballotById);
            this.repo.saveBallot(ballotById);
        }
        return new BallotById(ballotById);
    }

    public BallotById getBallot(BallotById ballotById){
        BallotByIdModel ballotId = this.repo.getBallot(this.repo.saveBallot(ballotById));
        return new BallotById(ballotId);
    }

    public void vote(int restaurantId, UUID ballotId, String voterName, String voterEmail) {
        BallotByIdModel ballot = this.repo.getBallot(ballotId);
        Vote v = new Vote(restaurantId, voterEmail, ballotId, voterName);
        ballot.setVotes(v.getVoteKey(), v);
        this.repo.saveBallot(ballot);
    }

//    public BallotChoicesModel getBallotChoices(UUID ballotId) {
//        BallotById ballotById = getBallot(ballotId);
//        this.repo.saveBallot(ballotById);
//        return ballotById.getBallotChoices();
//    }
}
