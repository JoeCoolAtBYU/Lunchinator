package barnett.joshua.lunchinator.repo;

import barnett.joshua.lunchinator.Application;
import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.exception.BallotNotFoundException;
import barnett.joshua.lunchinator.model.BallotAccessor;
import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.model.RestaurantAccessor;
import barnett.joshua.lunchinator.model.RestaurantModel;
import barnett.joshua.lunchinator.model.RestaurantReviewAccessor;
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
import java.util.Optional;
import java.util.UUID;

@Repository("cassandra")
public class Repo {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    Session session;

    private MappingManager mappingManager;
    private BallotAccessor ballotAccessor;
    private RestaurantAccessor restaurantAccessor;
    private RestaurantReviewAccessor restaurantReviewAccessor;
    private Mapper<BallotByIdModel> ballotModelMapper;
    private Mapper<RestaurantModel> restaurantMapper;
    private Mapper<RestaurantReviewModel> restaurantReviewMapper;

    @Autowired
    public Repo(Session session) {
        this.mappingManager = new MappingManager(session);

        this.ballotAccessor = this.mappingManager.createAccessor(BallotAccessor.class);
        this.restaurantAccessor = this.mappingManager.createAccessor(RestaurantAccessor.class);
        this.restaurantReviewAccessor = this.mappingManager.createAccessor(RestaurantReviewAccessor.class);

        this.ballotModelMapper = this.mappingManager.mapper(BallotByIdModel.class);
        this.restaurantMapper = this.mappingManager.mapper(RestaurantModel.class);
        this.restaurantReviewMapper = this.mappingManager.mapper(RestaurantReviewModel.class);

    }

    private Optional<BallotByIdModel> getBallotByIdModel(UUID ballotId) {
        return Optional.ofNullable(this.ballotAccessor.getBallotById(ballotId));

    }

    public BallotByIdModel getBallot(UUID ballotId) {
        return this.getBallotByIdModel(ballotId).orElseThrow(() -> new BallotNotFoundException(ballotId));
    }

    public UUID saveBallot(BallotById ballotById) {
        BallotByIdModel ballot1 = new BallotByIdModel(ballotById);
        saveBallot(ballot1);
        return ballot1.getBallotId();
    }

    public void saveBallot(BallotByIdModel ballot) {
        BatchStatement batchStatement = new BatchStatement();

        batchStatement.add(this.ballotModelMapper.saveQuery(ballot));

        this.session.execute(batchStatement);

    }

    public void saveRestaurant(RestaurantModel r) {
        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(this.restaurantMapper.saveQuery(r));
        this.session.execute(batchStatement);
    }

    public List<RestaurantModel> getRestaurants() {
        return this.restaurantAccessor.getAllRestaurants().all();
    }

    public void saveRestaurantReview(RestaurantReviewModel r) {
        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(this.restaurantReviewMapper.saveQuery(r));
        this.session.execute(batchStatement);
    }

    public List<RestaurantReviewModel> getRestaurantReviews() {
        return this.restaurantReviewAccessor.getAllReviews().all();
    }

    public RestaurantReviewModel getRestaurantReveiwsByName(String name) {
        return this.restaurantReviewAccessor.getRestaurantReview(name);
    }
}
