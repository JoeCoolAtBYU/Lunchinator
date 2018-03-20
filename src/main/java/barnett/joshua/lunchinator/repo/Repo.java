package barnett.joshua.lunchinator.repo;

import barnett.joshua.lunchinator.Application;
import barnett.joshua.lunchinator.domain.Ballot;
import barnett.joshua.lunchinator.model.BallotAccessor;
import barnett.joshua.lunchinator.model.BallotModel;
import barnett.joshua.lunchinator.model.RestaurantAccessor;
import barnett.joshua.lunchinator.model.RestaurantModel;
import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("cassandra")
public class Repo {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    Session session;

    private MappingManager mappingManager;
    private BallotAccessor ballotAccessor;
    private RestaurantAccessor restaurantAccessor;
    private Mapper<BallotModel> ballotModelMapper;
    private Mapper<RestaurantModel> restaurantMapper;
    private Mapper<RestaurantReviewModel> restaurantReviewModelMapper;

    @Autowired
    public Repo(Session session) {
        this.mappingManager = new MappingManager(session);
        this.ballotAccessor = this.mappingManager.createAccessor(BallotAccessor.class);
        this.restaurantAccessor = this.mappingManager.createAccessor(RestaurantAccessor.class);
        this.ballotModelMapper = this.mappingManager.mapper(BallotModel.class);
        this.restaurantMapper = this.mappingManager.mapper(RestaurantModel.class);
        this.restaurantReviewModelMapper = this.mappingManager.mapper(RestaurantReviewModel.class);

    }

    private BallotModel getBallotByIdModel(UUID ballotId) {
        return this.ballotAccessor.getBallotById(ballotId);

    }

    public Ballot getBallot(UUID ballotId) {
        BallotModel ballotByIdModel = this.getBallotByIdModel(ballotId);
        return new Ballot(ballotByIdModel, ballotByIdModel.getVoters());
    }

    public UUID saveBallot(Ballot ballot) {
        BallotModel ballot1 = new BallotModel(ballot);
        saveBallot(ballot1);
        return ballot1.getBallotId();
    }

    private void saveBallot(BallotModel ballot) {
        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(this.ballotModelMapper.saveQuery(ballot));
        this.session.execute(batchStatement);
    }

    public List<RestaurantModel> getRestaurants() {
        return this.restaurantAccessor.getAllRestaurants().all();
    }

    public void saveRestaurant(RestaurantModel model) {
        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(this.restaurantMapper.saveQuery(model));
        this.session.execute(batchStatement);
    }

    public RestaurantReviewModel getRestaurantReveiwByName(String name) {
        return this.restaurantAccessor.getRestaurantReviewByName(name);
    }

    public List<RestaurantReviewModel> getRestaurantReviews() {
        return this.restaurantAccessor.getRestaurantReviews().all();
    }

    public void saveRestaurantReview(RestaurantReviewModel reviewModel) {
        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(this.restaurantReviewModelMapper.saveQuery(reviewModel));
        this.session.execute(batchStatement);
    }
}
