package use_case.get_watchlist;

import entity.MovieList;

public class GetWatchlistOutputData {
    private final String username;
    private final MovieList watchlist;
    private final boolean useCaseFailed;

    public GetWatchlistOutputData(String username, MovieList watchlist, boolean useCaseFailed) {
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
