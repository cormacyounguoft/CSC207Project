package use_case.search_result;

/**
 * Input Boundary for actions which are related to search results.
 */
public interface SearchResultInputBoundary {

    /**
     * Executes the signup use case.
     * @param searchResultInputData the input data
     */
    void execute(SearchResultInputData searchResultInputData);
}
