package interface_adapter.search_result;

import use_case.search_result.SearchResultInputBoundary;

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

}
