package interface_adapter.get_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.watchlist.WatchlistState;
import interface_adapter.watchlist.WatchlistViewModel;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private WatchlistViewModel watchlistViewModel;

    public GetWatchlistPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.watchlistViewModel = watchlistViewModel;
    }

    @Override
    public void prepareSuccessView(GetWatchlistOutputData outputData) {
        final WatchlistState watchlistState = watchlistViewModel.getState();
        watchlistState.setUsername(outputData.getUsername());
        watchlistState.setWatchlistTitle(outputData.getWatchlistTitle());
        watchlistState.setWatchlistURL(outputData.getWatchlistURL());
        watchlistViewModel.setState(watchlistState);
        watchlistViewModel.firePropertyChanged();
        viewManagerModel.setState(watchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
