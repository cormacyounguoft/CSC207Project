package interface_adapter.watched_list;

import entity.MovieList;

public class WatchedListState {
    private String username;
    private MovieList watchedList;
    private String movieError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MovieList getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(MovieList watchedList) {
        this.watchedList = watchedList;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }
}
