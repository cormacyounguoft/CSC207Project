package use_case.watchlist;

import entity.MovieList;

public class WatchlistOutputData {
    private final String username;
    private final MovieList watchlist;
    private final boolean useCaseFailed;

    public WatchlistOutputData(String username, MovieList watchlist, boolean useCaseFailed) {
        this.username = username;
        this.watchlist = watchlist;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public MovieList getWatchlist() {
        return watchlist;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
