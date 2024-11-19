package interface_adapter.watchlist;

import entity.MovieList;
import use_case.watchlist.WatchlistInputBoundary;
import use_case.watchlist.WatchlistInputData;

import java.util.List;

public class WatchlistController {
    private final WatchlistInputBoundary interactor;

    public WatchlistController(WatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, List<String> watchedListTitle, List<String> watchedListURL) {
        final WatchlistInputData inputData = new WatchlistInputData(username, watchedListTitle, watchedListURL);
        interactor.execute(inputData);
    }
}
