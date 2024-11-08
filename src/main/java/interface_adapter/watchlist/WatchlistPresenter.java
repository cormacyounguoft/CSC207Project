package interface_adapter.watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watchlist.WatchlistOutputBoundary;
import use_case.watchlist.WatchlistOutputData;

public class WatchlistPresenter implements WatchlistOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final WatchlistViewModel watchlistViewModel;

    public WatchlistPresenter(ViewManagerModel viewManagerModel,
                              LoggedInViewModel loggedInViewModel,
                              WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.watchlistViewModel = watchlistViewModel;
    }


    @Override
    public void prepareSuccessView(WatchlistOutputData outputData) {
        watchlistViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView(WatchlistOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
