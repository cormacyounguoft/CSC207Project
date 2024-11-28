package use_case.watchlist_remove;

public class WatchlistRemoveInputData {
    private final String username;
    private final String movieTitle;

    public WatchlistRemoveInputData(String username, String movieTitle) {
        this.username = username;
        this.movieTitle = movieTitle;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return movieTitle;
    }
}
