package use_case.watchlist_remove;

public class WatchlistRemoveOutputData {
    private final boolean useCaseFailed;
    private final String username;

    public WatchlistRemoveOutputData(boolean useCaseFailed, String username) {
        this.useCaseFailed = useCaseFailed;
        this.username = username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
