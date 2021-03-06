package barnett.joshua.lunchinator.model.accessors;

import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface RestaurantReviewAccessor {

    @Query("Select * from restaurantReview")
    Result<RestaurantReviewModel> getAllReviews();

    @Query("Select * from restaurantReview where name = ?")
    RestaurantReviewModel getRestaurantReview(String name);
}
