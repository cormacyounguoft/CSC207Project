package use_case.watchlist_remove;

/**
 * Data access interface for watchlist remove.
 */
public interface WatchlistRemoveDataAccessInterface {

    /**
     * Removes the movie from the watchlist for user.
     * @param username username of the user removing movie for.
     * @param movieTitle title of the movie being removed from the watchlist.
     */
    void removeFromWatchlist(String username, String movieTitle);
}
