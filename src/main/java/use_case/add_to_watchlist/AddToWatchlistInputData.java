package use_case.add_to_watchlist;

/**
 * The Input Data for the add to watchlist Use Case.
 */
public class AddToWatchlistInputData {
    private final String username;
    private final String movie;

    public AddToWatchlistInputData(String username, String movie) {
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
