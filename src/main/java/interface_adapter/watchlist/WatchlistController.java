package interface_adapter.watchlist;

import entity.MovieList;
import use_case.watchlist.WatchlistInputBoundary;
import use_case.watchlist.WatchlistInputData;

public class WatchlistController {
    private final WatchlistInputBoundary interactor;

    public WatchlistController(WatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, MovieList watchlist) {
        final WatchlistInputData inputData = new WatchlistInputData(username, watchlist);
        interactor.execute(inputData);
    }

    public void switchToLoggedInView(String username) {
        final WatchlistInputData inputData = new WatchlistInputData(username, new MovieList());
        interactor.switchToLoggedInView(inputData);
    }
}
