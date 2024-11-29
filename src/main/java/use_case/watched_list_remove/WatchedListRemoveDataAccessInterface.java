package use_case.watched_list_remove;

/**
 * Data access interface for searching for watched list remove.
 */
public interface WatchedListRemoveDataAccessInterface {

    /**
     * Removes the movie from the watched list for user.
     * @param username username of the user removing movie for.
     * @param title title of the movie being removed from the watchlist.
     */
    void removeFromWatchedlist(String username, String title);
}
