package interface_adapter.watchlistremove;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist.WatchlistViewModel;
import use_case.watchlist.WatchlistOutputData;
import use_case.watchlistremove.watchlistremoveOutputBoundary;
import use_case.watchlistremove.watchlistremoveOutputData;

public class WatchlistremovePresenter implements watchlistremoveOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WatchlistViewModel watchlistViewModel;

    public WatchlistremovePresenter(ViewManagerModel viewManagerModel,
                              WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistViewModel = watchlistViewModel;
    }

    public void prepareSuccessView() {
        watchlistViewModel.firePropertyChanged();
    }

}