package use_case.watchlistremove;

public interface WatchlistremoveDataAccessInterface {
    void removeMovieWatchlist(String username, String movieTitle);
}