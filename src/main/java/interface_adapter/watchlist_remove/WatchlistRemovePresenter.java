package interface_adapter.watchlist_remove;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watchlist_remove.WatchlistRemoveOutputBoundary;
import use_case.watchlist_remove.WatchlistRemoveOutputData;

public class WatchlistRemovePresenter implements WatchlistRemoveOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public WatchlistRemovePresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WatchlistRemoveOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        this.loggedInViewModel.setState(state);
        this.loggedInViewModel.firePropertyChanged();
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}