package barnett.joshua.lunchinator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    int id;
    String restaurant;
    String reviewer;
    String rating;
    String review;
    String reviewerImage;
}
