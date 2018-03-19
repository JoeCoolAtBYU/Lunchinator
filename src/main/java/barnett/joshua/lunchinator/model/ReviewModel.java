package barnett.joshua.lunchinator.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UDT(keyspace = "lunch", name = "review")
public class ReviewModel {

    @Field(name = "id")
    int id;

    @Field(name="restaurant")
    String restaurant;

    @Field(name = "reviewr")
    String reviewer;

    @Field(name = "rating")
    String rating;

    @Field(name = "review")
    String review;

    @Field(name = "reviewerImage")
    String reviewerImage;

}
