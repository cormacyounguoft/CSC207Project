package interface_adapter.search_result;

import entity.Movie;
import use_case.search_result.SearchResultInputBoundary;
import use_case.search_result.SearchResultInputData;

/**
 * Controller for the Search Result Use Case.
 */
public class SearchResultController {
    private final SearchResultInputBoundary searchResultUseCaseInteractor;

    public SearchResultController(SearchResultInputBoundary searchResultUseCaseInteractor) {
        this.searchResultUseCaseInteractor = searchResultUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     */
    public void execute() {
        searchResultUseCaseInteractor.execute();
    }

    /**
     * Switch to the home view.
     */
    public void switchToHomeView() {
        searchResultUseCaseInteractor.switchToHomeView();
    }
}
