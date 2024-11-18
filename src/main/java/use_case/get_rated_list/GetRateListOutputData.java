package use_case.get_rated_list;

import entity.UserRating;

import java.util.Map;

public class GetRateListOutputData {
    private final String username;
    private final Map<String, Integer> userRating;
    private final boolean useCaseFailed;


    public GetRateListOutputData(String username, Map<String, Integer> userRating, boolean useCaseFailed) {
        this.username = username;
        this.userRating = userRating;
        this.useCaseFailed = useCaseFailed;
    }
    public String getUsername(){
        return this.username;
    }
    public Map<String, Integer> getUserRating(){
        return this.userRating;
    }
}
