package use_case.get_watched_list;

/**
 * Output boundary for get watched list.
 */
public interface GetWatchedListOutputBoundary {

    /**
     * Prepares the success view for get watched list.
     * @param outputData the output data for the get watched list use case.
     */
    void prepareSuccessView(GetWatchedListOutputData outputData);
}
