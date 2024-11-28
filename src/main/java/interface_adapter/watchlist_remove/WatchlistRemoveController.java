package interface_adapter.watchlist_remove;

import use_case.watchlist_remove.WatchlistRemoveInputBoundary;
import use_case.watchlist_remove.WatchlistRemoveInputData;

public class WatchlistRemoveController {
    private final WatchlistRemoveInputBoundary interactor;

    public WatchlistRemoveController(WatchlistRemoveInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, String movieTitle) {
        final WatchlistRemoveInputData inputData = new WatchlistRemoveInputData(username, movieTitle);
        interactor.execute(inputData);
    }
}