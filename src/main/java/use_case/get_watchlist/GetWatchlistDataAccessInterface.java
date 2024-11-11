package use_case.get_watchlist;

import entity.MovieList;

public interface GetWatchlistDataAccessInterface {

    MovieList getWatchlist(String username);
}
