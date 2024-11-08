package use_case.logged_in_search_result;

/**
 * Input Boundary for actions which are related to logged in search results.
 */
public interface LoggedInSearchResultInputBoundary {

    /**
     * Executes the logged in search result use case.
     * @param loggedInSearchResultInputData the input data
     */
    void execute(LoggedInSearchResultInputData loggedInSearchResultInputData);

    /**
     * Executes the switch to Logged In view use case.
     */
    void switchToLoggedInView(LoggedInSearchResultInputData loggedInSearchResultInputData);

    void switchToRateView(LoggedInSearchResultInputData loggedInSearchResultInputData);
}
