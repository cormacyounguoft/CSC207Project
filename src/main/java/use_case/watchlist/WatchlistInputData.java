package use_case.watchlist;

import java.util.List;

public class WatchlistInputData {
    private final String username;
    private final List<String> watchlistTitle;
    private final List<String> watchlistURL;

    public WatchlistInputData(String username, List<String> watchlistTitle, List<String> watchlistURL) {
        this.username = username;
        this.watchlistTitle = watchlistTitle;
        this.watchlistURL = watchlistURL;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getWatchlistTitle() {
        return watchlistTitle;
    }

    public List<String> getWatchlistURL() {
        return watchlistURL;
    }
}
