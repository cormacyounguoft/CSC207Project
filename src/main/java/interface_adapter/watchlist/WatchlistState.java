package interface_adapter.watchlist;

import entity.MovieList;

import java.util.List;

public class WatchlistState {
    private String username;
    private List<String> watchlistTitle;
    private List<String> watchlistURL;
    private String movieError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getWatchlistTitle() {
        return watchlistTitle;
    }

    public void setWatchlistTitle(List<String> watchlistTitle) {
        this.watchlistTitle = watchlistTitle;
    }

    public List<String> getWatchlistURL() {
        return watchlistURL;
    }

    public void setWatchlistURL(List<String> watchlistURL) {
        this.watchlistURL = watchlistURL;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }
}
