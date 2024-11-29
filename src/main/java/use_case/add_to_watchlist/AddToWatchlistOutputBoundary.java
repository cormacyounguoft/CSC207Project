package use_case.add_to_watchlist;

/**
 * The output boundary for the Add to watchlist Use Case.
 */
public interface AddToWatchlistOutputBoundary {

    /**
     * Prepares the success view when add to watched list.
     * @param outputData output data when added to watch list.
     */
    void prepareSuccessView(AddToWatchlistOutputData outputData);

    /**
     * Prepares the fail view when add to watch list.
     * @param errorMessage error message displayed when fail view executed.
     */
    void prepareFailView(String errorMessage);
}
