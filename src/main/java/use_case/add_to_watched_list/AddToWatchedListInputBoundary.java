package use_case.add_to_watched_list;

/**
 * Input Boundary for adding to watched list.
 */
public interface AddToWatchedListInputBoundary {
    void execute(AddToWatchedListInputData inputData);
}
