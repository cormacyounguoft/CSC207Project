package use_case.search_result;

/**
 * The Search Result Interactor.
 */
public class SearchResultInteractor implements SearchResultInputBoundary {
    private final SearchResultOutputBoundary searchResultPresenter;

    public SearchResultInteractor(SearchResultOutputBoundary searchResultOutputBoundary) {
        this.searchResultPresenter = searchResultOutputBoundary;
    }

    @Override
    public void execute(SearchResultInputData searchResultInputData) {

        final SearchResultOutputData searchResultOutputData = new SearchResultOutputData(false);
        searchResultPresenter.prepareSuccessView(searchResultOutputData);
    }

    @Override
    public void switchToHomeView() {
        searchResultPresenter.switchToHomeView();
    }
}
