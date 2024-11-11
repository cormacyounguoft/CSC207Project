package use_case.logged_in_search;

/**
 * The output boundary for the Logged In Search Use Case.
 */
public interface LoggedInSearchOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     * @param loggedInSearchOutputData the output data
     */
    void prepareSuccessView(LoggedInSearchOutputData loggedInSearchOutputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Logged In Search View.
     */
    void switchToLoggedInView(LoggedInSearchOutputData loggedInSearchOutputData);
}
