package use_case.add_to_watched_list;

/**
 * Input Boundary for adding to watched list.
 */
public interface AddToWatchedListInputBoundary {

    /**
     * Executes the add to watched list function.
     * @param inputData the input data required to add to watched list.
     */
    void execute(AddToWatchedListInputData inputData);
}
