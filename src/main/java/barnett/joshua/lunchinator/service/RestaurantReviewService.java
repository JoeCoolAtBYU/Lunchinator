package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import barnett.joshua.lunchinator.repo.Repo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

@Service
public class RestaurantReviewService {
    @Autowired
    Repo repo;


    public void getAllReviews() {
        String uri = "https://interview-project-17987.herokuapp.com/api/reviews";
        RestTemplate restTemplate = new RestTemplate();
        JsonNode result = restTemplate.getForObject(uri, JsonNode.class);

        List<RestaurantReviewModel> restaurantReviewModels = this.repo.getRestaurantReviews();
        if (result.size() != restaurantReviewModels.size()) {
            populateRestaurantReviewTable(result);
        }
    }

    private void populateRestaurantReviewTable(JsonNode result) {
        Iterator<JsonNode> iterator = result.iterator();

        while (iterator.hasNext()) {
            RestaurantReviewModel r = new RestaurantReviewModel();
            JsonNode nextRestObject = iterator.next();

            r.setId(nextRestObject.get("Id").asInt());
            r.setName(nextRestObject.get("restaurant").asText());
            r.setAverageReview(nextRestObject.get("rating").asText());
            r.setTopReviewer(nextRestObject.get("reviewer").asText());
            r.setReview(nextRestObject.get("review").asText());

            this.repo.saveRestaurantReview(r);
        }
    }
}
