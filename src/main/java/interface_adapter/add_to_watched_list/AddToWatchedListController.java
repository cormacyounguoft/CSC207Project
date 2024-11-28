package interface_adapter.add_to_watched_list;

import use_case.add_to_watched_list.AddToWatchedListInputBoundary;
import use_case.add_to_watched_list.AddToWatchedListInputData;

/**
 * The controller for the Add To Watchlist Use Case.
 */
public class AddToWatchedListController {
    private AddToWatchedListInputBoundary useCaseInteractor;

    public AddToWatchedListController(AddToWatchedListInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * Execute the add to watched list controller.
     * @param username the username of the User
     * @param movie the movie to add to watched list.
     */
    public void execute(String username, String movie) {
        final AddToWatchedListInputData inputData = new AddToWatchedListInputData(username, movie);
        useCaseInteractor.execute(inputData);
    }
}
