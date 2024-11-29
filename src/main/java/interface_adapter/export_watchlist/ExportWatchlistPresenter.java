package interface_adapter.export_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist_remove.WatchlistRemoveState;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;
import use_case.export_watchlist.ExportWatchlistOutputBoundary;
import use_case.export_watchlist.ExportWatchlistOutputData;

public class ExportWatchlistPresenter implements ExportWatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchlistRemoveViewModel watchlistRemoveViewModel;

    public ExportWatchlistPresenter(ViewManagerModel viewManagerModel,
                                    WatchlistRemoveViewModel watchlistRemoveViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistRemoveViewModel = watchlistRemoveViewModel;
    }

    @Override
    public void prepareSuccessView(ExportWatchlistOutputData outputData) {
        final WatchlistRemoveState watchlistRemoveState = watchlistRemoveViewModel.getState();
        watchlistRemoveState.setUsername(outputData.getUsername());
        watchlistRemoveState.setExport("Watchlist successfully exported");
        watchlistRemoveViewModel.setState(watchlistRemoveState);
        watchlistRemoveViewModel.firePropertyChanged();

        viewManagerModel.setState(watchlistRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchlistRemoveState watchlistRemoveState = watchlistRemoveViewModel.getState();
        watchlistRemoveState.setError(errorMessage);
        watchlistRemoveViewModel.firePropertyChanged();
    }
}
