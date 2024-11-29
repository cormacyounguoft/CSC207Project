package use_case.logged_in;

/**
 * The output boundary for the Logged In Use Case.
 */
public interface LoggedInOutputBoundary {

    /**
     * Switches to the Change Password View.
     * @param loggedInOutputData Output data for logged in use case.
     */
    void switchToChangePasswordView(LoggedInOutputData loggedInOutputData);

    /**
     * Switches to the Logged In Search View.
     * @param loggedInOutputData Output data for logged in use case.
     */
    void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData);
}
