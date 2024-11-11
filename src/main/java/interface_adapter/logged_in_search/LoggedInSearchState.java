package interface_adapter.logged_in_search;

/**
 * The state for the Logged In Search View Model.
 */
public class LoggedInSearchState {
    private String username = "";
    private String searchQuery = "";
    private String searchError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }
}
