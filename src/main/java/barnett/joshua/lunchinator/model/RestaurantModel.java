package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.Restaurant;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Table(keyspace = "lunch", name = "restaurant")
public class RestaurantModel {

    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "waitTimeMinutes")
    int waitTimeMinutes;

    @Column(name = "types")
    List<String> types;

    @Column(name = "image")
    String image;

    @Column(name = "description")
    String description;

    public RestaurantModel(Restaurant restaurant){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.waitTimeMinutes = restaurant.getWaitTimeMinutes();
        this.types = restaurant.getTypes();
        this.image = restaurant.getImage();
        this.description = restaurant.getDescription();
    }
}
