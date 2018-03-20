package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface RestaurantAccessor {

    @Query("Select * from restaurant where id = ?")
    RestaurantModel getRestaurantById(int restaurantId);

    @Query("Select * from restaurant")
    Result<RestaurantModel> getAllRestaurants();

    @Query("Select * from restaurantReview where name = ?")
    RestaurantReviewModel getRestaurantReviewByName(String name);

    @Query("Select * from restaurantReview")
    Result<RestaurantReviewModel> getRestaurantReviews();
}
