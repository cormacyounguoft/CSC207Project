package use_case.watched_list_remove;

public interface WatchedListRemoveDataAccessInterface {
    void removeFromWatchedlist(String username, String title);
}
