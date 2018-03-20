package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.BallotChoicesModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BallotChoices {

    Choice suggestion;
    List<Choice> choices;

    public BallotChoices(BallotChoicesModel choicesModel) {
        this.choices = choicesModel.getChoices().stream().map(c -> new Choice(c)).collect(Collectors.toList());
        this.suggestion = new Choice(choicesModel.getSuggestion());
    }

    public BallotChoices(){
        this.choices = new ArrayList<>();
        Choice c = new Choice();
        c.setId(-1);
        this.suggestion = c;
    }

}
