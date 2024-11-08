package use_case.logged_in;

/**
 * The output boundary for the Logged In Use Case.
 */
public interface LoggedInOutputBoundary {
    /**
     * Switches to the Change Password View.
     */
    void switchToChangePasswordView(LoggedInOutputData loggedInOutputData);

    /**
     * Switches to the Logged In Search View.
     */
    void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData);

    /**
     * Switches to the Watch List View.
     */
    void switchToWatchListView(LoggedInOutputData loggedInOutputData);

    /**
     * Switches to the Watched List View.
     */
    void switchToWatchedListView(LoggedInOutputData loggedInOutputData);
}
