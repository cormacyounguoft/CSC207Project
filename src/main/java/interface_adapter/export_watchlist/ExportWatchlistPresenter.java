package interface_adapter.export_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;
import interface_adapter.watchlist_remove.WatchlistState;
import use_case.export_watchlist.ExportWatchlistOutputBoundary;
import use_case.export_watchlist.ExportWatchlistOutputData;

/**
 * The presenter for the export list use case.
 */
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
        final WatchlistState watchlistState = watchlistRemoveViewModel.getState();
        watchlistState.setUsername(outputData.getUsername());
        watchlistState.setExport("Watchlist successfully exported");
        watchlistRemoveViewModel.setState(watchlistState);
        watchlistRemoveViewModel.firePropertyChanged();

        viewManagerModel.setState(watchlistRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchlistState watchlistState = watchlistRemoveViewModel.getState();
        watchlistState.setError(errorMessage);
        watchlistRemoveViewModel.firePropertyChanged();
    }
}
