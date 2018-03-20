package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.RestaurantReview;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(keyspace = "lunch", name = "restaurantReview")
public class RestaurantReviewModel implements Comparable<RestaurantReviewModel> {

    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "averageReview")
    String averageReview;

    @Column(name = "topReviewer")
    String topReviewer;

    @Column(name = "review")
    String review;

    public RestaurantReviewModel(RestaurantReview restaurantReview) {
        this.id = restaurantReview.getId();
        this.name = restaurantReview.getName();
        this.averageReview = restaurantReview.getAverageReview();
        this.topReviewer = restaurantReview.getTopReviewer();
        this.review = restaurantReview.getReview();
    }

    @Override
    public int compareTo(RestaurantReviewModel o) {
        return Integer.parseInt(o.getAverageReview()) - Integer.parseInt(this.getAverageReview());
    }
}
