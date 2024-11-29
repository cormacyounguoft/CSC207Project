package use_case.logged_in_search_result;

/**
 * Output Boundary for actions which are related to logged in search results.
 */
public interface LoggedInSearchResultOutputBoundary {

    /**
     * Prepares the success view for the logged in search result Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LoggedInSearchResultOutputData outputData);

    /**
     * Prepares the failure view for the Search Result Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
