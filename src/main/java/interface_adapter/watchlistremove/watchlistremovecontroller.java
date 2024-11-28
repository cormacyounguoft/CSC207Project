package interface_adapter.watchlistremove;

import entity.MovieList;
import use_case.watchlistremove.WatchlistremoveInputBoundary;
import use_case.watchlistremove.watchlistremoveInputData;

import java.util.List;

public class watchlistremovecontroller {
    private final WatchlistremoveInputBoundary interactor;

    public watchlistremovecontroller(WatchlistremoveInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, String movieTitle, String posterURL) {
        final watchlistremoveInputData inputData = new watchlistremoveInputData(username, movieTitle, posterURL);
        interactor.execute(inputData);
    }
}