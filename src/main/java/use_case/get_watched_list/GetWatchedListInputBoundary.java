package use_case.get_watched_list;

/**
 * Input boundary for get watched list.
 */
public interface GetWatchedListInputBoundary {

    /**
     * Execute the get watched list use case.
     * @param inputData input data for get watched list.
     */
    void execute(GetWatchedListInputData inputData);
}
