package use_case.logged_in_search_result;

/**
 * The Input Data for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultInputData {

    private final String username;
    private final String movie;

    public LoggedInSearchResultInputData(String username, String movie) {
        this.username = username;
        this.movie = movie;
    }

    public String getUsername() {
        return username;
    }

    public String getMovie() {
        return movie;
    }
}
