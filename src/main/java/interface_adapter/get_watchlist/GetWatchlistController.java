package interface_adapter.get_watchlist;

import use_case.get_watchlist.GetWatchlistInputBoundary;
import use_case.get_watchlist.GetWatchlistInputData;

/**
 * The controller for the get watchlist use case.
 */
public class GetWatchlistController {

    private final GetWatchlistInputBoundary interactor;

    public GetWatchlistController(GetWatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the controller for the get watchlist use case.
     * @param username the username
     */
    public void execute(String username) {
        final GetWatchlistInputData inputData = new GetWatchlistInputData(username);
        interactor.execute(inputData);
    }
}
