package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.model.BallotByIdModel;
import barnett.joshua.lunchinator.model.BallotChoicesModel;
import barnett.joshua.lunchinator.model.RestaurantModel;
import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import barnett.joshua.lunchinator.repo.Repo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
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

        List<RestaurantModel> restaurantModels = this.repo.getRestaurants();
        if (result.size() != restaurantModels.size()) {
            populateRestaurantTable(result);

        }
    }

    private void populateRestaurantTable(JsonNode result) {

        for (JsonNode aResult : result) {
            List<String> restTypes = new ArrayList<>();
            RestaurantModel r = new RestaurantModel();

            r.setId(aResult.get("id").asInt());
            r.setName(aResult.get("name").asText());
            if (aResult.get("waitTimeMinutes") == null) {
                r.setWaitTimeMinutes(aResult.get("waitTimeMin").asInt());
            } else {
                r.setWaitTimeMinutes(aResult.get("waitTimeMinutes").asInt());
            }

            JsonNode types = aResult.get("types");

            if (types == null) {
                types = aResult.get("type");
            }

            if (types != null && types.isArray()) {
                for (JsonNode type : types) {
                    restTypes.add(type.asText());
                }
            }

            r.setTypes(restTypes);

            JsonNode image = aResult.get("image");
            if (image != null) {
                r.setImage(image.asText());
            } else {
                r.setImage("");
            }

            r.setDescription(aResult.get("description").asText());

            this.repo.saveRestaurant(r);
        }
    }

    public void getFiveRestaurants(BallotByIdModel ballotModel) {
        Map<Integer, RestaurantModel> fiveRestaurants = new HashMap<>();
        List<RestaurantModel> fiveRestaurantsList;
        List<RestaurantReviewModel> reviewModels = new ArrayList<>();

        if (ballotModel.getBallotChoices() == null || ballotModel.getBallotChoices().getSuggestion().getId() == -1) {
            getFiveRandomRestaurants(fiveRestaurants);
        }

        fiveRestaurantsList = new ArrayList<>(fiveRestaurants.values());

        for (RestaurantModel r : fiveRestaurantsList) {
            reviewModels.add(this.repo.getRestaurantReviewsByName(r.getName()));
        }

        ballotModel.setBallotChoices(new BallotChoicesModel(reviewModels));
    }

    private void getFiveRandomRestaurants(Map<Integer, RestaurantModel> fiveRestaurants) {
        List<RestaurantModel> restaurants = this.repo.getRestaurants();

        Map<Integer, RestaurantModel> restMap = restaurants.stream().collect(Collectors.toMap(RestaurantModel::getId, restaurantModel -> restaurantModel));

        while (fiveRestaurants.size() < 5) {
            Random random = new Random();
            int next = random.nextInt(restMap.size() - 1) + 1;
            fiveRestaurants.put(next, restMap.get(next));
        }

    }
}
