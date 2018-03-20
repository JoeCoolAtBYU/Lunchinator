package barnett.joshua.lunchinator.service;

import barnett.joshua.lunchinator.model.RestaurantReviewModel;
import barnett.joshua.lunchinator.repo.Repo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        for (JsonNode aResult : result) {
            RestaurantReviewModel r = new RestaurantReviewModel();

            r.setId(aResult.get("Id").asInt());
            r.setName(aResult.get("restaurant").asText());
            r.setAverageReview(aResult.get("rating").asText());
            r.setTopReviewer(aResult.get("reviewer").asText());
            r.setReview(aResult.get("review").asText());

            this.repo.saveRestaurantReview(r);
        }
    }
}
