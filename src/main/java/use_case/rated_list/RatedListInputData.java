package use_case.rated_list;

import java.util.Map;

public class RatedListInputData {
    private final String username;
    private final Map<String, Integer> rating;

    public RatedListInputData(String username, Map<String, Integer> rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername(){
        return this.username;
    }

    public Map<String, Integer> getRating(){
        return this.rating;
    }
}
