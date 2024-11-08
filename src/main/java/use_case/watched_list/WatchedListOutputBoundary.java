package use_case.watched_list;

public interface WatchedListOutputBoundary {
    void prepareSuccessView(WatchedListOutputData outputData);

    void switchToLoggedInView(WatchedListOutputData outputData);
}
