package use_case.watchlist;

public interface WatchlistInputBoundary {

    void execute(WatchlistInputData inputData);

    /**
     * Executes the switch to Logged In view use case.
     */
    void switchToLoggedInView(WatchlistInputData inputData);
}
