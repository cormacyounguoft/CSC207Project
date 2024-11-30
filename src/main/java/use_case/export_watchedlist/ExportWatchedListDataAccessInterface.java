package use_case.export_watchedlist;

import entity.MovieList;

/**
 * DAO for exporting watched list.
 */
public interface ExportWatchedListDataAccessInterface {
    /**
     * Gets the watched list of the user.
     * @param username the username whose watched list is being retrieved.
     * @return the watched list for user [username].
     */
    MovieList getWatchedList(String username);
}
