package interface_adapter.add_to_watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import use_case.add_to_watched_list.AddToWatchedListOutputBoundary;
import use_case.add_to_watched_list.AddToWatchedListOutputData;

/**
 * The presenter for the Add To Watched list.
 */
public class AddToWatchedListPresenter implements AddToWatchedListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public AddToWatchedListPresenter(ViewManagerModel viewManagerModel,
                                     LoggedInSearchResultViewModel loggedInSearchResultViewModel,
                                     LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(AddToWatchedListOutputData outputData) {
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
