package use_case.get_watchlist;

/**
 * Input data for get watch list.
 */
public class GetWatchlistInputData {

    private final String username;

    public GetWatchlistInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
