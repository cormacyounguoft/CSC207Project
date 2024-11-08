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

    public void switchToLoggedInSearchView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToLoggedInSearchView(loggedInInputData);
    }

    public void switchToChangePasswordView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToChangePasswordView(loggedInInputData);
    }

    public void switchToWatchListView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToWatchListView(loggedInInputData);
    }

    public void switchToWatchedListView(String username) {
        final LoggedInInputData loggedInInputData = new LoggedInInputData(username);
        loggedInUseCaseInteractor.switchToWatchedListView(loggedInInputData);
    }
}
