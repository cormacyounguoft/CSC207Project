package interface_adapter.add_to_watchlist;

import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInputData;

/**
 * The controller for the Add To Watchlist Use Case.
 */
public class AddToWatchlistController {
    private final AddToWatchlistInputBoundary useCaseInteractor;

    public AddToWatchlistController(AddToWatchlistInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * The controller for the Add To Watchlist Use Case.
     * @param username The username of the user adding to watchlist.
     * @param movie The movie being added to watchlist.
     */
    public void execute(String username, String movie) {
        final AddToWatchlistInputData inputData = new AddToWatchlistInputData(username, movie);
        useCaseInteractor.execute(inputData);
    }
}
