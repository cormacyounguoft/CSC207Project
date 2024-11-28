package interface_adapter.add_to_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;
import use_case.add_to_watchlist.AddToWatchlistOutputData;

/**
 * The presenter for the Add To Watchlist.
 */
public class AddToWatchlistPresenter implements AddToWatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public AddToWatchlistPresenter(ViewManagerModel viewManagerModel,
                                   LoggedInSearchResultViewModel loggedInSearchResultViewModel,
                                   LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(AddToWatchlistOutputData outputData) {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(outputData.getUsername());
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoggedInSearchResultState loggedInSearchResultState = loggedInSearchResultViewModel.getState();
        loggedInSearchResultState.setMovieError(errorMessage);
        loggedInSearchResultViewModel.firePropertyChanged();
    }
}
