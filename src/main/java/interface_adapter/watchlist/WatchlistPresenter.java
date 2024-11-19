package interface_adapter.watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watchlist.WatchlistOutputBoundary;
import use_case.watchlist.WatchlistOutputData;

public class WatchlistPresenter implements WatchlistOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WatchlistViewModel watchlistViewModel;

    public WatchlistPresenter(ViewManagerModel viewManagerModel,
                              WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistViewModel = watchlistViewModel;
    }


    @Override
    public void prepareSuccessView(WatchlistOutputData outputData) {
        watchlistViewModel.firePropertyChanged();
    }
}
