package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.Choice;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name = "choice")
public class ChoiceModel implements Comparable<ChoiceModel> {
    @Field(name = "id")
    int id;
    @Field(name = "name")
    String name;
    @Field(name = "averageReview")
    String averageReview;
    @Field(name = "topReviewer")
    String topReviewer;
    @Field(name = "review")
    String review;

    public ChoiceModel(Choice choice) {
        this.id = choice.getId();
        this.name = choice.getName();
        this.averageReview = choice.getAverageReview();
        this.topReviewer = choice.getTopReviewer();
        this.review = choice.getReview();
    }

    public ChoiceModel(RestaurantReviewModel model) {
        this.id = model.getRestaurantId();
        this.name = model.getName();
        this.averageReview = model.getAverageReview();
        this.topReviewer = model.getTopReviewer();
        this.review = model.getReview();
    }

    @Override
    public int compareTo(ChoiceModel o) {
        return Integer.parseInt(o.getAverageReview()) - Integer.parseInt(this.getAverageReview());
    }
}
