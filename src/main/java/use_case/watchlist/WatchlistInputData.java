package use_case.watchlist;

import entity.MovieList;

public class WatchlistInputData {
    private final String username;
    private final MovieList watchlist;

    public WatchlistInputData(String username, MovieList watchlist) {
        this.username = username;
        this.watchlist = watchlist;
    }

    public String getUsername() {
        return username;
    }

    public MovieList getWatchlist() {
        return watchlist;
    }
}
