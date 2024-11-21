package interface_adapter.watched_list;

import entity.MovieList;

import java.util.List;

public class WatchedListState {
    private String username;
    private List<String> watchedListTitle;
    private List<String> watchedListURL;
    private String movieError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public void setWatchedListTitle(List<String> watchedListTitle) {
        this.watchedListTitle = watchedListTitle;
    }

    public List<String> getWatchedListURL() {
        return watchedListURL;
    }

    public void setWatchedListURL(List<String> watchedListURL) {
        this.watchedListURL = watchedListURL;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }
}
