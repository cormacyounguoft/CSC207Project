package interface_adapter.search_result;

import interface_adapter.ViewManagerModel;
import use_case.search_result.SearchResultOutputBoundary;
import use_case.search_result.SearchResultOutputData;

/**
 * The Presenter for the Search Result Use Case.
 */
public class SearchResultPresenter implements SearchResultOutputBoundary {
    private final SearchResultViewModel searchResultViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchResultPresenter(ViewManagerModel viewManagerModel,
                                 SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchResultOutputData response) {}
}
