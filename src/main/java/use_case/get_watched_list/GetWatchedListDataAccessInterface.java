package use_case.get_watched_list;

import entity.MovieList;

/**
 * Data access interface for get watched list use case.
 */
public interface GetWatchedListDataAccessInterface {

    /**
     * Get the watched list.
     * @param username username of user to obtain watched list.
     * @return watched list.
     */
    MovieList getWatchedList(String username);
}
