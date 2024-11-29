package use_case.watchlist_remove;

/**
 * Input boundary for watch list remove.
 */
public interface WatchlistRemoveInputBoundary {

    /**
     * Exeute the input boundary for watch list remove.
     * @param inputData input data for the watch list remove use caase.
     */
    void execute(WatchlistRemoveInputData inputData);
}
