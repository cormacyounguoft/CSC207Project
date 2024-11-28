package use_case.get_rated_list;

import java.util.List;
import java.util.Map;

/**
 * The Output Data for Get Rate List.
 */
public class GetRateListOutputData {
    private final String username;
    private final Map<String, List<String>> userRating;
    private final boolean useCaseFailed;

    public GetRateListOutputData(String username, Map<String, List<String>> userRating, boolean useCaseFailed) {
        this.username = username;
        this.userRating = userRating;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return this.username;
    }

    public Map<String, List<String>> getUserRating() {
        return this.userRating;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
