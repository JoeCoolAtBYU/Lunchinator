package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.model.BallotChoicesModel;
import barnett.joshua.lunchinator.model.BallotModel;
import barnett.joshua.lunchinator.model.RestaurantModel;
import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import barnett.joshua.lunchinator.repo.Repo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    Repo repo;


    public void getAllRestaurants() {
        String uri = "https://interview-project-17987.herokuapp.com/api/restaurants";
        RestTemplate restTemplate = new RestTemplate();
        JsonNode result = restTemplate.getForObject(uri, JsonNode.class);

        List<RestaurantModel> restaurantModels = repo.getRestaurants();
        if (result.size() != restaurantModels.size()) {
            populateRestaurantTable(result);
        }
    }

    private List<RestaurantModel> populateRestaurantTable(JsonNode result) {

        Iterator<JsonNode> iterator = result.iterator();
        List<RestaurantModel> restaurantModels = new ArrayList<>();

        while (iterator.hasNext()) {
            List<String> restTypes = new ArrayList<>();
            RestaurantModel r = new RestaurantModel();
            JsonNode nextRestObject = iterator.next();

            r.setId(nextRestObject.get("id").asInt());
            r.setName(nextRestObject.get("name").asText());
            if (nextRestObject.get("waitTimeMinutes") == null) {
                r.setWaitTimeMinutes(nextRestObject.get("waitTimeMin").asInt());
            } else {
                r.setWaitTimeMinutes(nextRestObject.get("waitTimeMinutes").asInt());
            }

            JsonNode types = nextRestObject.get("types");

            if (types == null) {
                types = nextRestObject.get("type");
            }

            if (types != null && types.isArray()) {
                for (JsonNode type : types) {
                    restTypes.add(type.asText());
                }
            }

            r.setTypes(restTypes);

            JsonNode image = nextRestObject.get("image");
            if (image != null) {
                r.setImage(image.asText());
            } else {
                r.setImage("");
            }

            r.setDescription(nextRestObject.get("description").asText());

            repo.saveRestaurant(r);

            restaurantModels.add(r);
        }
        return restaurantModels;
    }

    public void getFiveRestaurants(BallotModel ballotModel) {
        Map<Integer, RestaurantModel> fiveRestaurants = new HashMap<>();
        List<RestaurantModel> fiveRestaurantsList;
        List<RestaurantReviewModel> reviewModels = new ArrayList<>();

        if (ballotModel.getBallotChoices() == null) {
            getFiveRandomRestaurants(fiveRestaurants);
        }

        fiveRestaurantsList = new ArrayList<>(fiveRestaurants.values());

        for (RestaurantModel r : fiveRestaurantsList) {
            reviewModels.add(repo.getRestaurantReveiwByName(r.getName()));
        }

        ballotModel.setBallotChoices(new BallotChoicesModel(reviewModels));
    }

    private void getFiveRandomRestaurants(Map<Integer, RestaurantModel> fiveRestaurants) {
        List<RestaurantModel> restaurants = repo.getRestaurants();

        Map<Integer, RestaurantModel> restMap = restaurants.stream().collect(Collectors.toMap(RestaurantModel::getId, restaurantModel -> restaurantModel));

        while (fiveRestaurants.size() < 5) {
            Random random = new Random();
            int next = random.nextInt(restMap.size() - 1) + 1;
            fiveRestaurants.put(next, restMap.get(next));
        }

    }
}
