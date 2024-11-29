package use_case.get_watchlist;

import entity.MovieList;

/**
 * DAO for get watch list.
 */
public interface GetWatchlistDataAccessInterface {

    /**
     * Gets the watchlist of the user.
     * @param username the username watchlist if being obtained for.
     */
    MovieList getWatchlist(String username);
}
