package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.RestaurantModel;
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
    String description;

    public Restaurant(RestaurantModel restaurant){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.waitTimeMinutes = restaurant.getWaitTimeMinutes();
        this.types = restaurant.getTypes();
        this.image = restaurant.getImage();
        this.description = restaurant.getDescription();
    }
}
