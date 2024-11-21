package interface_adapter.search_result;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.search_result.SearchResultOutputBoundary;
import use_case.search_result.SearchResultOutputData;

/**
 * The Presenter for the Search Result Use Case.
 */
public class SearchResultPresenter implements SearchResultOutputBoundary {
    private final SearchResultViewModel searchResultViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public SearchResultPresenter(ViewManagerModel viewManagerModel,
                                 SearchResultViewModel searchResultViewModel,
                                 HomeViewModel homeViewModel) {
        this.searchResultViewModel = searchResultViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SearchResultOutputData response) {
        searchResultViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setMovieError(error);
        searchResultViewModel.firePropertyChanged();
    }

}
