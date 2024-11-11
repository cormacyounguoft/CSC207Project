package use_case.add_to_watched_list;

/**
 * The output boundary for the Add to watched list Use Case.
 */
public interface AddToWatchedListOutputBoundary {
    void prepareSuccessView(AddToWatchedListOutputData outputData);

    void prepareFailView(String errorMessage);
}
