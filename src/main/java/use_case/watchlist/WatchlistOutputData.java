package use_case.watchlist;

import entity.MovieList;

import java.util.List;

public class WatchlistOutputData {
    private final String username;
    private final List<String> watchlistTitle;
    private final List<String> watchlistURL;
    private final boolean useCaseFailed;

    public WatchlistOutputData(String username, List<String> watchlistTitle, List<String> watchlistURL,
                               boolean useCaseFailed) {
        this.username = username;
        this.watchlistTitle = watchlistTitle;
        this.watchlistURL = watchlistURL;
        this.useCaseFailed = useCaseFailed;
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

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
