package interface_adapter.watched_list;

import entity.MovieList;
import use_case.watched_list.WatchedListInputBoundary;
import use_case.watched_list.WatchedListInputData;

public class WatchedListController {
    private final WatchedListInputBoundary interactor;

    public WatchedListController(WatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, MovieList watchlist) {
        final WatchedListInputData inputData = new WatchedListInputData(username, watchlist);
        interactor.execute(inputData);
    }

    public void switchToLoggedInView(String username) {
        final WatchedListInputData inputData = new WatchedListInputData(username, new MovieList());
        interactor.switchToLoggedInView(inputData);
    }
}
