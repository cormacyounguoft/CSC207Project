package interface_adapter.search_result;

import use_case.search_result.SearchResultOutputBoundary;
import use_case.search_result.SearchResultOutputData;

/**
 * The Presenter for the Search Result Use Case.
 */
public class SearchResultPresenter implements SearchResultOutputBoundary {

    private final SearchResultViewModel searchResultViewModel;

    public SearchResultPresenter(SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;
    }

    @Override
    public void prepareSuccessView(SearchResultOutputData response) {
        searchResultViewModel.firePropertyChanged();
    }
}
