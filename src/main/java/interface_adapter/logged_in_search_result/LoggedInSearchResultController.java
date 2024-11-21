package interface_adapter.logged_in_search_result;

import entity.Movie;
import use_case.logged_in_search_result.LoggedInSearchResultInputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultInputData;

/**
 * Controller for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultController {
    private final LoggedInSearchResultInputBoundary useCaseInteractor;

    public LoggedInSearchResultController(LoggedInSearchResultInputBoundary interactor) {
        this.useCaseInteractor = interactor;
    }

    public void execute(String username, String movie) {
        final LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData(username, movie);
        useCaseInteractor.execute(inputData);
    }
}
