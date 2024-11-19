package use_case.search_result;

/**
 * Input Boundary for actions which are related to search results.
 */
public interface SearchResultInputBoundary {

    /**
     * Executes the signup use case.
     */
    void execute();

    /**
     * Executes the switch to Home view use case.
     */
    void switchToHomeView();
}
