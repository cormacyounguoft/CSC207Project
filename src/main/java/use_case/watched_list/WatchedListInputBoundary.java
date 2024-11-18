package use_case.watched_list;

public interface WatchedListInputBoundary {

    void execute(WatchedListInputData inputData);

    /**
     * Executes the switch to Logged In view use case.
     */
    void switchToLoggedInView(WatchedListInputData inputData);

//    void switchToRateView(WatchedListInputData inputData);
}
