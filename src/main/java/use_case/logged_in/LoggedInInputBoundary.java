package use_case.logged_in;

/**
 * Input Boundary for actions which are related to the logged in view.
 */
public interface LoggedInInputBoundary {

    /**
     * Executes the switch to search view use case.
     * @param loggedInInputData Input data for logged in use case.
     */
    void switchToLoggedInSearchView(LoggedInInputData loggedInInputData);

    /**
     * Executes the switch to change password view use case.
     * @param loggedInInputData Input data for logged in use case.
     */
    void switchToChangePasswordView(LoggedInInputData loggedInInputData);
}
