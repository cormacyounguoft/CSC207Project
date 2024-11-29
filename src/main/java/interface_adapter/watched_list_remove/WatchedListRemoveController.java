package interface_adapter.watched_list_remove;

import use_case.watched_list_remove.WatchedListRemoveInputBoundary;
import use_case.watched_list_remove.WatchedListRemoveInputData;

/**
 * The controller for the watched list remove Use Case.
 */
public class WatchedListRemoveController {

    private final WatchedListRemoveInputBoundary interactor;

    public WatchedListRemoveController(WatchedListRemoveInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the watched list use case.
     * @param username username of the user.
     * @param title title of the movie in the watched list.
     */
    public void execute(String username, String title) {
        final WatchedListRemoveInputData inputData = new WatchedListRemoveInputData(username, title);
        interactor.execute(inputData);
    }
}
