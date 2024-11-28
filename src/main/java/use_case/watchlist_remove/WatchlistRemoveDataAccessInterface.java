package use_case.watchlist_remove;

public interface WatchlistRemoveDataAccessInterface {
    void removeFromWatchlist(String username, String movieTitle);
}