package use_case.watchlistremove;

import java.util.List;

public class watchlistremoveInputData {
    private final String username;
    private final String movieTitle;
    private final String posterURL;

    public watchlistremoveInputData(String username, String movieTitle, String posterURL) {
        this.username = username;
        this.movieTitle = movieTitle;
        this.posterURL = posterURL;
    }

    public String getUsername() {
        return username;
    }

    public String getWatchlistremoveTitle() {
        return movieTitle;
    }

    public String getWatchlistremoveURL() {
        return posterURL;
    }
}
