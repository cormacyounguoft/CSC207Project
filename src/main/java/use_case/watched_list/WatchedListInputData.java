package use_case.watched_list;

import entity.MovieList;

import java.util.List;

public class WatchedListInputData {
    private final String username;
    private final String title;

    public WatchedListInputData(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }
}
