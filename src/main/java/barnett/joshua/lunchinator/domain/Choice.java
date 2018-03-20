package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.ChoiceModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Choice {
    int id;
    String name;
    String averageReview;
    String topReviewer;
    String review;

    public Choice(ChoiceModel choice) {
        this.id = choice.getId();
        this.name = choice.getName();
        this.averageReview = choice.getAverageReview();
        this.topReviewer = choice.getTopReviewer();
        this.review = choice.getReview();
    }
}
