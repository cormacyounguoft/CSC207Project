package interface_adapter.ratedList;

import java.util.List;
import java.util.Map;

/**
 * The state for the rated list use case.
 */
public class RatedListState {

    private String username;
    private Map<String, List<String>> rating;

    public Map<String, List<String>> getRating() {
        return rating;
    }

    public void setRating(Map<String, List<String>> rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
