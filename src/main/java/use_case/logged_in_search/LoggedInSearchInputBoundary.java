package use_case.logged_in_search;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface LoggedInSearchInputBoundary {

    /**
     * Executes the search use case.
     * @param loggedInSearchInputData the input data
     */
    void execute(LoggedInSearchInputData loggedInSearchInputData);
}
