package use_case.watched_list;

import entity.MovieList;

import java.util.List;

public class WatchedListOutputData {
    private final String username;

    public WatchedListOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
