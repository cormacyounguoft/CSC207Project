package use_case.add_to_watchlist;

/**
 * The output boundary for the Add to watchlist Use Case.
 */
public interface AddToWatchlistOutputBoundary {
    void prepareSuccessView(AddToWatchlistOutputData outputData);

    void prepareFailView(String errorMessage);
}
