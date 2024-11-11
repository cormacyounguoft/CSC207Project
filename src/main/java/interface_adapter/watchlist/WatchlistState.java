package interface_adapter.watchlist;

import entity.MovieList;

public class WatchlistState {
    private String username;
    private MovieList watchlist;
    private String movieError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MovieList getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(MovieList watchlist) {
        this.watchlist = watchlist;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }
}
