package interface_adapter.watched_list;

import use_case.watched_list.WatchedListInputBoundary;
import use_case.watched_list.WatchedListInputData;

/**
 * The controller for the watched list Use Case.
 */
public class WatchedListController {
    private final WatchedListInputBoundary interactor;

    public WatchedListController(WatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the watched list use case.
     * @param username username of the user.
     * @param title title of the movie in the watched list.
     */
    public void execute(String username, String title) {
        final WatchedListInputData inputData = new WatchedListInputData(username, title);
        interactor.execute(inputData);
    }
}
