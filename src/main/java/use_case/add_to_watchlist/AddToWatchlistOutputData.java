package use_case.add_to_watchlist;

/**
 * Output Data for the add to watchlist Use Case.
 */
public class AddToWatchlistOutputData {
    private String username;
    private boolean useCaseFailed;

    public AddToWatchlistOutputData(String username, boolean useCaseFailed) {
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
