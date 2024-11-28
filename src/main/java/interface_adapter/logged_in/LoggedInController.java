package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInputData;

/**
 * Controller for the Logged In Use Case.
 */
public class LoggedInController {
    private final LoggedInInputBoundary loggedInUseCaseInteractor;

    public LoggedInController(LoggedInInputBoundary loggedInUseCaseInteractor) {
        this.loggedInUseCaseInteractor = loggedInUseCaseInteractor;
    }

    /**
     * Switches the current view to the logged in search view for a specific user.
     * @param username the username of the user for whom the logged in search view is displayed
     */
    public void switchToLoggedInSearchView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToLoggedInSearchView(loggedInInputData);
    }

    /**
     * Switches the current view to the change password view for a specific user.
     * @param username the username of the user for whom the change password view is displayed
     */
    public void switchToChangePasswordView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToChangePasswordView(loggedInInputData);
    }

    /**
     * Switches the current view to the dashboard view for a specific user.
     * @param username the username of the user for whom the dashboard view is displayed
     */
    public void switchToDashboardView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToDashboardView(loggedInInputData);
    }

    /**
     * Switches the current view to the watchlist view for a specific user.
     * @param username the username of the user for whom the watch list view is displayed
     */
    public void switchToWatchListView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToWatchListView(loggedInInputData);
    }

    /**
     * Switches the current view to the watched list view for a specific user.
     * @param username the username of the user for whom the watched list view is displayed
     */
    public void switchToWatchedListView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToWatchedListView(loggedInInputData);
    }
}
