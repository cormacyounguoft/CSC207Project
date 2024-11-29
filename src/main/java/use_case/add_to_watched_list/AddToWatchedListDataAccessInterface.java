package use_case.add_to_watched_list;

import entity.Movie;

/**
 * DAO for the Add To Watched list Use Case.
 */
public interface AddToWatchedListDataAccessInterface {

    /**
     * Gets the current username of the user.
     * @return username of User.
     */
    String getCurrentUsername();

    /**
     * Saves the movie to the users watched list.
     * @param username username of the user.
     * @param movie movie saving to watched list.
     */
    void saveToWatchedList(String username, Movie movie);
}
