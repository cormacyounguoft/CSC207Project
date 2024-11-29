package use_case.add_to_watchlist;

import entity.Movie;

/**
 * DAO for the Add To Watchlist Use Case.
 */
public interface AddToWatchlistDataAccessInterface {

    /**
     * Gets the current username of the user adding to watchlist.
     * @return the username.
     */
    String getCurrentUsername();

    /**
     * Prepares the success view when add to watched list.
     * @param username Username of the user.
     * @param movie Movie being added to watchlist.
     */
    void saveToWatchlist(String username, Movie movie);
}
