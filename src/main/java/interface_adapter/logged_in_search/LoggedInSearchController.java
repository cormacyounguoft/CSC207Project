package interface_adapter.logged_in_search;

import use_case.logged_in_search.LoggedInSearchInputBoundary;
import use_case.logged_in_search.LoggedInSearchInputData;

/**
 * Controller for the Logged In Search Use Case.
 */
public class LoggedInSearchController {
    private final LoggedInSearchInputBoundary loggedInSearchUseCaseInteractor;

    public LoggedInSearchController(LoggedInSearchInputBoundary loggedInSearchUseCaseInteractor) {
        this.loggedInSearchUseCaseInteractor = loggedInSearchUseCaseInteractor;
    }

    public void switchToLoggedInView(String username) {
        final LoggedInSearchInputData loggedInSearchInputData = new LoggedInSearchInputData(username, "");
        loggedInSearchUseCaseInteractor.switchToLoggedInView(loggedInSearchInputData);
    }

    /**
     * Executes the Search Use Case.
     * @param searchQuery the search query.
     * @param username the username
     */
    public void execute(String searchQuery, String username) {
        final LoggedInSearchInputData loggedInSearchInputData = new LoggedInSearchInputData(username, searchQuery);
        loggedInSearchUseCaseInteractor.execute(loggedInSearchInputData);
    }
}
