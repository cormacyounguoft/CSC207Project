package use_case.get_watchlist;

/**
 * Output boundary for get watch list.
 */
public interface GetWatchlistOutputBoundary {

    /**
     * Prepares the success view for get watch list.
     * @param outputData the output data after getting the watch list.
     */
    void prepareSuccessView(GetWatchlistOutputData outputData);
}
