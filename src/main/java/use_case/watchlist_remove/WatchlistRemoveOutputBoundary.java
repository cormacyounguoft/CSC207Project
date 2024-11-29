package use_case.watchlist_remove;

/**
 * Output Boundary for watch list remove.
 */
public interface WatchlistRemoveOutputBoundary {

    /**
     * Success view preparation for watch list remove.
     * @param outputData Output data for watchlist remove.
     */
    void prepareSuccessView(WatchlistRemoveOutputData outputData);
}
