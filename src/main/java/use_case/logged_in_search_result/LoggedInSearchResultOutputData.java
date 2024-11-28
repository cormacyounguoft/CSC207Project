package use_case.logged_in_search_result;

/**
 * Output Data for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultOutputData {
    private final String username;
    private final String movie;
    private final boolean useCaseFailed;

    public LoggedInSearchResultOutputData(String username, String movie, boolean useCaseFailed) {
        this.username = username;
        this.movie = movie;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getMovie() {
        return movie;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
