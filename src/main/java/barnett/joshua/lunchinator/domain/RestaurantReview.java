package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReview {
    int id;
    String name;
    String averageReview;
    String topReviewer;
    String review;

    RestaurantReview(RestaurantReviewModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.averageReview = model.getAverageReview();
        this.topReviewer = model.getTopReviewer();
        this.review = model.getReview();
    }

}
