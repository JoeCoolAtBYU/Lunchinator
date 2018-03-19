package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name = "restaurant")
public class RestaurantModel {

    @Field(name = "id")
    int id;

    @Field(name = "name")
    String name;

    @Field(name = "waitTimeMinutes")
    int waitTimeMinutes;

    @Field(name = "types")
    List<String> types;

    @Field(name = "image")
    String image;

    @Field(name = "description")
    String description;

    @Field(name = "votes")
    int votes;

    @Field(name = "dateTime")
    String dateTime;

    @Field(name = "averageReview")
    String averageReview;

    @Field(name = "topReviewer")
    String topReviewer;
}
