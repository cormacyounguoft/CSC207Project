package use_case.search_result;


/**
 * The output boundary for the Search Result Use Case.
 */
public interface SearchResultOutputBoundary {
    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchResultOutputData outputData);
}
