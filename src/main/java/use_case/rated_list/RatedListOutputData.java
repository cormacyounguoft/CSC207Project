package use_case.rated_list;

import java.util.Map;

public class RatedListOutputData {
    private final String username;
    private final Map<String, Integer> rating;
    private final boolean useCaseFailed;

    public RatedListOutputData(String username, Map<String, Integer> rating, boolean useCaseFailed) {
        this.username = username;
        this.rating = rating;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername(){
        return this.username;
    }

    public Map<String, Integer> getRating(){
        return this.rating;
    }
}
