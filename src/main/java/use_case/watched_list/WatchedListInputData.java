package use_case.watched_list;

import entity.MovieList;

import java.util.List;

public class WatchedListInputData {
    private final String username;
    private final List<String> watchedListTitle;
    private final List<String> watchedListURL;

    public WatchedListInputData(String username, List<String> watchedListTitle, List<String> watchedListURL) {
        this.username = username;
        this.watchedListTitle = watchedListTitle;
        this.watchedListURL = watchedListURL;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getWatchedListTitle() {
        return watchedListTitle;
    }

    public List<String> getWatchedListURL() {
        return watchedListURL;
    }
}
