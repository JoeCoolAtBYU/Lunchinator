package barnett.joshua.lunchinator.model;

import barnett.joshua.lunchinator.domain.BallotChoices;
import com.datastax.driver.mapping.annotations.UDT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UDT(name = "ballotChoices")
public class BallotChoicesModel {

    ChoiceModel suggestion;
    List<ChoiceModel> choices;

    public BallotChoicesModel (List<RestaurantReviewModel> restaurantReviewModels){
        this.choices = restaurantReviewModels.stream().map(rr -> new ChoiceModel(rr)).collect(Collectors.toList());
        populateSuggestion(this.choices);
        Collections.shuffle(this.choices);
    }

    public BallotChoicesModel(BallotChoices ballotChoices) {
        this.suggestion = new ChoiceModel(ballotChoices.getSuggestion());
        this.choices = ballotChoices.getChoices().stream().map(choice -> new ChoiceModel(choice)).collect(Collectors.toList());
    }

    private void populateSuggestion(List<ChoiceModel> choiceList) {
        Collections.sort(this.choices);
        this.suggestion = this.choices.get(0);
    }
}