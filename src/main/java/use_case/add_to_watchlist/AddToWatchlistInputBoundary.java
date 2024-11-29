package use_case.add_to_watchlist;

/**
 * Input Boundary for adding to watchlist.
 */
public interface AddToWatchlistInputBoundary {

    /**
     * Executes the add to watched list function.
     * @param inputData the input data required to add to watch list.
     */
    void execute(AddToWatchlistInputData inputData);
}
