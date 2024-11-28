package interface_adapter.watchlist_remove;

import java.util.List;

public class WatchlistRemoveState {
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

    public void removeMovie(String movieTitle, String movieURL) {
        int index_of_title = watchlistTitle.indexOf(movieTitle);
        int index_of_poster = watchlistURL.indexOf(movieURL);

        if (index_of_poster >= 0) {
            watchlistURL.remove(index_of_poster);
        }
        if (index_of_title >= 0) {
            watchlistTitle.remove(index_of_title);
        }
    }

}
