package use_case.rated_list;

import java.util.List;
import java.util.Map;

public class RatedListOutputData {
    private final Map<String, List<String>> userRating;

    public RatedListOutputData(Map<String, List<String>> userRating) {
        this.userRating = userRating;

    }

    public Map<String, List<String>> getUserRating() {
        return userRating;
    }
}
