package use_case.get_watchlist;

/**
 * Input boundary for get watch list.
 */
public interface GetWatchlistInputBoundary {

    /**
     * Execute the get watch list function.
     * @param inputData input data for execution.
     */
    void execute(GetWatchlistInputData inputData);
}
