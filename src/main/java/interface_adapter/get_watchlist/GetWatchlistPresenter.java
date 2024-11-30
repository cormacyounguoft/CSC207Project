package interface_adapter.get_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist_remove.WatchlistState;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;
import use_case.get_watchlist.GetWatchlistOutputBoundary;
import use_case.get_watchlist.GetWatchlistOutputData;

/**
 * The presenter for the get watchlist use case.
 */
public class GetWatchlistPresenter implements GetWatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchlistRemoveViewModel watchlistViewModel;

    public GetWatchlistPresenter(ViewManagerModel viewManagerModel, WatchlistRemoveViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistViewModel = watchlistViewModel;
    }

    @Override
    public void prepareSuccessView(GetWatchlistOutputData outputData) {
        final WatchlistState watchlistState = watchlistViewModel.getState();
        watchlistState.setUsername(outputData.getUsername());
        watchlistState.setWatchlistTitle(outputData.getWatchlistTitle());
        watchlistState.setWatchlistUrl(outputData.getWatchlistUrl());
        watchlistViewModel.setState(watchlistState);
        watchlistViewModel.firePropertyChanged();
        viewManagerModel.setState(watchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
