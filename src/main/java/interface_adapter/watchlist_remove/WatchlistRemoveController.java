package interface_adapter.watchlist_remove;

import use_case.watchlist_remove.WatchlistRemoveInputBoundary;
import use_case.watchlist_remove.WatchlistRemoveInputData;

/**
 * The controller for the watchlist remove Use Case.
 */
public class WatchlistRemoveController {

    private final WatchlistRemoveInputBoundary interactor;

    public WatchlistRemoveController(WatchlistRemoveInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the controller for the watchlist remove Use Case.
     * @param username the username.
     * @param movieTitle the movie title.
     */
    public void execute(String username, String movieTitle) {
        final WatchlistRemoveInputData inputData = new WatchlistRemoveInputData(username, movieTitle);
        interactor.execute(inputData);
    }
}
