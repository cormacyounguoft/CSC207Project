package use_case.logged_in_search;

/**
 * The Input Data for the Logged Search Use Case.
 */
public class LoggedInSearchInputData {

    private final String username;
    private final String searchQuery;

    public LoggedInSearchInputData(String username, String searchQuery) {
        this.username = username;
        this.searchQuery = searchQuery;
    }

    public String getUsername() {
        return username;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
