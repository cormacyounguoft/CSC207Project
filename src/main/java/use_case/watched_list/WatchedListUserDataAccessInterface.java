package use_case.watched_list;

import entity.Movie;

public interface WatchedListUserDataAccessInterface {
    void removeFromWatchedlist(String username, String title);
}
