package use_case.watched_list_remove;

/**
 * Output Boundary for watched list remove.
 */
public interface WatchedListRemoveOutputBoundary {

    /**
     * Success view preparation for watch list remove.
     * @param outputData Output data for watched list remove.
     */
    void prepareSuccessView(WatchedListRemoveOutputData outputData);

}
