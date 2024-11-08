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
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private final RateViewModel rateViewModel;

    public LoggedInSearchResultPresenter(ViewManagerModel viewManagerModel,
                                         LoggedInViewModel loggedInViewModel,
                                         LoggedInSearchResultViewModel loggedInSearchResultViewModel,
                                         RateViewModel rateViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.rateViewModel = rateViewModel;
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

    @Override
    public void switchToLoggedInView(LoggedInSearchResultOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRateView(LoggedInSearchResultOutputData outputData) {
        final RateState state = rateViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setMovie(outputData.getMovie());
        rateViewModel.setState(state);
        rateViewModel.firePropertyChanged();

        viewManagerModel.setState(rateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
