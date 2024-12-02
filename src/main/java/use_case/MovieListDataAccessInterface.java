package use_case;

import entity.MovieList;

/**
 * DAO for get watch list.
 */
public interface MovieListDataAccessInterface {

    /**
     * Gets the watchlist of the user.
     * @param username the username watchlist if being obtained for.
     * @return The watch list
     */
    MovieList getWatchlist(String username);
    MovieList getWatchedList(String username);


}
