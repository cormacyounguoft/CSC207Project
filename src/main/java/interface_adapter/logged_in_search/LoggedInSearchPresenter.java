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
    private final LoggedInViewModel loggedInViewModel;

    public LoggedInSearchPresenter(ViewManagerModel viewManagerModel,
                                   LoggedInSearchViewModel loggedInSearchViewModel,
                                   LoggedInSearchResultViewModel loggedInSearchResultViewModel,
                                   LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInSearchViewModel = loggedInSearchViewModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInSearchOutputData response) {
        final LoggedInSearchResultState loggedInSearchResultState = loggedInSearchResultViewModel.getState();
        loggedInSearchResultState.setResult(response.getUsername(), response.getMovie());
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

    @Override
    public void switchToLoggedInView(LoggedInSearchOutputData loggedInSearchOutputData) {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(loggedInSearchOutputData.getUsername());
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
