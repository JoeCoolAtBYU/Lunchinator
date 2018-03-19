package barnett.joshua.lunchinator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    int id;
    String name;
    int waitTimeMinutes;
    List<String> types;
    String image;
    String descrioption;
    int votes;
    String dateTime;
    String averageReview;
    String topReviewer;
}
