package use_case.add_to_watchlist;

import entity.Movie;

/**
 * DAO for the Add To Watchlist Use Case.
 */
public interface AddToWatchlistDataAccessInterface {

    String getCurrentUsername();

    void saveToWatchlist(String username, Movie movie);
}
