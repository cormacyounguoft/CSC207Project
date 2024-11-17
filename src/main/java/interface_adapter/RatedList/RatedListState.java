package interface_adapter.RatedList;

import java.util.Map;

public class RatedListState {
    private String username;
    private Map<String, Integer> rating;

    public Map<String, Integer> getRating() {
        return rating;
    }

    public void setRating(Map<String, Integer> rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
