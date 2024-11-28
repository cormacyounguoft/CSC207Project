package use_case.watched_list_remove;

public class WatchedListRemoveInputData {
    private final String username;
    private final String title;

    public WatchedListRemoveInputData(String username, String title) {
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
