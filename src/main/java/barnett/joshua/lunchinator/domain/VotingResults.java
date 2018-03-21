package barnett.joshua.lunchinator.domain;

import barnett.joshua.lunchinator.model.Vote;
import barnett.joshua.lunchinator.model.VoteKey;
import barnett.joshua.lunchinator.repo.Repo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Component
public class VotingResults {

    Winner winner;
    List<VotingChoices> choices;

    @JsonIgnore
    Repo repo;

    @JsonIgnore
    private Map<Integer, Integer> votesPerRestaurant;

    public VotingResults(BallotById ballot, Repo repo) {
        this.repo = repo;
        calculateWinner(ballot.getVotes(), ballot);
        this.choices = ballot.getBallotChoices().getChoices().stream().map(choice -> new VotingChoices(choice, this.votesPerRestaurant)).collect(Collectors.toList());
    }

    private void calculateWinner(Map<VoteKey, Vote> votes, BallotById ballot) {
        int highestScore = 0;
        Map<Integer, Integer> voteCounterMap = new HashMap<>();

        for (Vote vote : votes.values()) {
            int restaurantId = vote.getRestaurantId();

            if (voteCounterMap.isEmpty() || !voteCounterMap.containsKey(restaurantId)) {
                voteCounterMap.put(restaurantId, 1);
            } else {
                voteCounterMap.put(restaurantId, voteCounterMap.get(restaurantId) + 1);
            }
        }

        this.votesPerRestaurant = voteCounterMap;
        int numberOfVotes = voteCounterMap.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(1).findFirst().get().getValue();

        int restaurantId = voteCounterMap.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(1).findFirst().get().getKey();

        String restaurantName = this.repo.getRestaurantById(restaurantId).getName();

        this.winner = new Winner(restaurantId, numberOfVotes, restaurantName, ballot);

    }
}

@Data
class Winner {
    int id;
    String datetime;
    String name;
    int votes;

    Winner(int restaurantId, Integer numberOfVotes, String restaurantName, BallotById ballot) {
        this.id = restaurantId;
        this.datetime = new Date().toString();
        this.name = restaurantName;
        this.votes = numberOfVotes;
    }
}

@Data
class VotingChoices {
    int id;
    String name;
    int votes;

    public VotingChoices(Choice choice, Map<Integer, Integer> votesPerRestaurant) {
        this.id = choice.getId();
        this.name = choice.getName();
        Integer votes = votesPerRestaurant.get(choice.getId());
        if (votes == null) {
            this.votes = 0;
        } else {
            this.votes = votes;
        }
    }
}
