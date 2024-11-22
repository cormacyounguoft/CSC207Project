package interface_adapter.logged_in_search_result;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.rate.RateState;
import interface_adapter.rate.RateViewModel;
import use_case.logged_in_search_result.LoggedInSearchResultOutputBoundary;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;

/**
 * The Presenter for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultPresenter implements LoggedInSearchResultOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;

    public LoggedInSearchResultPresenter(ViewManagerModel viewManagerModel,
                                         LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInSearchResultOutputData outputData) {
        loggedInSearchResultViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoggedInSearchResultState searchResultState = loggedInSearchResultViewModel.getState();
        searchResultState.setMovieError(errorMessage);
        loggedInSearchResultViewModel.firePropertyChanged();
    }


}
