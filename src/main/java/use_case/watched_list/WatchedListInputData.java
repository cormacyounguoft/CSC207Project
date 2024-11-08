package use_case.watched_list;

import entity.MovieList;

public class WatchedListInputData {
    private final String username;
    private final MovieList watchedList;

    public WatchedListInputData(String username, MovieList watchedList) {
        this.username = username;
        this.watchedList = watchedList;
    }

    public String getUsername() {
        return username;
    }

    public MovieList getWatchedList() {
        return watchedList;
    }
}
