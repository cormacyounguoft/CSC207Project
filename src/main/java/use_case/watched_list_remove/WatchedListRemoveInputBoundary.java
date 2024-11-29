package use_case.watched_list_remove;

/**
 * Input boundary for watched list remove.
 */
public interface WatchedListRemoveInputBoundary {

    /**
     * Execute the input boundary for watch list remove.
     * @param inputData input data for the watch list remove use caase.
     */
    void execute(WatchedListRemoveInputData inputData);
}
