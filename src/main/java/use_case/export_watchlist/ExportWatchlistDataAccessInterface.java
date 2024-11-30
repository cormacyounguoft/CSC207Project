package use_case.export_watchlist;

import entity.MovieList;

/**
 * DAO for export watch list.
 */
public interface ExportWatchlistDataAccessInterface {

    /**
     * Gets the watchlist of the user.
     * @param username the username watchlist if being obtained for.
     * @return returns the Watchlist of username user.
     */
    MovieList getWatchlist(String username);
}
