package use_case.watched_list_remove;

public class WatchedListRemoveOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public WatchedListRemoveOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
