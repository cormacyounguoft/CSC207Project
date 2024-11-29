package use_case.add_to_watched_list;

/**
 * The output boundary for the Add to watched list Use Case.
 */
public interface AddToWatchedListOutputBoundary {

    /**
     * Prepares the success view when add to watched list.
     * @param outputData output data when added to watched list.
     */
    void prepareSuccessView(AddToWatchedListOutputData outputData);

    /**
     * Prepares the fail view when add to watched list.
     * @param errorMessage error message displayed when fail view executed.
     */
    void prepareFailView(String errorMessage);
}
