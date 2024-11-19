package interface_adapter.logged_in_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import use_case.logged_in_search.LoggedInSearchOutputBoundary;
import use_case.logged_in_search.LoggedInSearchOutputData;

/**
 * The Presenter for the Logged In Search Use Case.
 */
public class LoggedInSearchPresenter implements LoggedInSearchOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInSearchViewModel loggedInSearchViewModel;
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;

    public LoggedInSearchPresenter(ViewManagerModel viewManagerModel,
                                   LoggedInSearchViewModel loggedInSearchViewModel,
                                   LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInSearchViewModel = loggedInSearchViewModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInSearchOutputData response) {
        final LoggedInSearchResultState loggedInSearchResultState = loggedInSearchResultViewModel.getState();
        loggedInSearchResultState.setTitle(response.getTitle());
        loggedInSearchResultState.setReleaseDate(response.getReleaseDate());
        loggedInSearchResultState.setDescription(response.getDescription());
        loggedInSearchResultState.setRottenTomatoes(response.getRottenTomatoes());
        loggedInSearchResultState.setRuntime(response.getRuntime());
        loggedInSearchResultState.setGenre(response.getGenre());
        loggedInSearchResultState.setActors(response.getActors());
        loggedInSearchResultState.setDirector(response.getDirector());
        loggedInSearchResultState.setPosterLink(response.getPosterLink());

        loggedInSearchResultViewModel.setState(loggedInSearchResultState);
        loggedInSearchResultViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInSearchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoggedInSearchState loggedInSearchState = loggedInSearchViewModel.getState();
        loggedInSearchState.setSearchError(errorMessage);
        loggedInSearchViewModel.firePropertyChanged();
    }
}
