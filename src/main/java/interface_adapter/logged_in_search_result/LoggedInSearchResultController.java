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

    public void switchToLoggedInView(String username) {
        final LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData(username, new Movie());
        useCaseInteractor.switchToLoggedInView(inputData);
    }

    public void execute(String username, Movie movie) {
        final LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData(username, movie);
        useCaseInteractor.execute(inputData);
    }

    public void switchToRateView(String username, Movie movie) {
        final LoggedInSearchResultInputData inputData = new LoggedInSearchResultInputData(username, movie);
        useCaseInteractor.switchToRateView(inputData);
    }
}
