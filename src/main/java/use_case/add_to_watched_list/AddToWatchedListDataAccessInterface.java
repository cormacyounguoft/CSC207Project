package use_case.add_to_watched_list;

import entity.Movie;

/**
 * DAO for the Add To Watched list Use Case.
 */
public interface AddToWatchedListDataAccessInterface {

    String getCurrentUsername();

    void saveToWatchedList(String username, Movie movie);
}
