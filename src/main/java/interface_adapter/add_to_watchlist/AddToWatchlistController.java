package interface_adapter.add_to_watchlist;

import entity.Movie;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInputData;

/**
 * The controller for the Add To Watchlist Use Case.
 */
public class AddToWatchlistController {
    private AddToWatchlistInputBoundary useCaseInteractor;

    public AddToWatchlistController(AddToWatchlistInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void execute(String username, Movie movie) {
        final AddToWatchlistInputData inputData = new AddToWatchlistInputData(username, movie);
        useCaseInteractor.execute(inputData);
    }
}
