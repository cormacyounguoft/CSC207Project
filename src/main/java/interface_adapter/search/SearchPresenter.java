package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

/**
 * The Presenter for the Search Use Case.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final SearchResultViewModel searchResultViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,
                           SearchViewModel searchViewModel,
                           SearchResultViewModel searchResultViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchResultViewModel = searchResultViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        final SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setTitle(response.getTitle());
        searchResultState.setReleaseDate(response.getReleaseDate());
        searchResultState.setDescription(response.getDescription());
        searchResultState.setRottenTomatoes(response.getRottenTomatoes());
        searchResultState.setRuntime(response.getRuntime());
        searchResultState.setGenre(response.getGenre());
        searchResultState.setActors(response.getActors());
        searchResultState.setDirector(response.getDirector());
        searchResultState.setPoster(response.getPoster());
        this.searchResultViewModel.setState(searchResultState);
        searchResultViewModel.firePropertyChanged();

        viewManagerModel.setState(searchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(error);
        searchViewModel.firePropertyChanged();
    }
}
