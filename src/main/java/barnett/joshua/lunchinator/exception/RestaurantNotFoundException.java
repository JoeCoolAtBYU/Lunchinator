package barnett.joshua.lunchinator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(int id) {
        super("There are no Restaurants with the id of: " + id);
    }
}
