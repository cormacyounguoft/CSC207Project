package interface_adapter.get_watched_list;

import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInputData;

/**
 * The controller for the get watched list use case.
 */
public class GetWatchedListController {

    private final GetWatchedListInputBoundary interactor;

    public GetWatchedListController(GetWatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the controller for the get watched list use case.
     * @param username the username
     */
    public void execute(String username) {
        final GetWatchedListInputData inputData = new GetWatchedListInputData(username);
        interactor.execute(inputData);
    }
}
