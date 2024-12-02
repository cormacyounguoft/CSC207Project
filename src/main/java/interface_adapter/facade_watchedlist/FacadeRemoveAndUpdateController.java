package interface_adapter.facade_watchedlist;

import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInputData;
import use_case.watched_list_remove.WatchedListRemoveInputBoundary;
import use_case.watched_list_remove.WatchedListRemoveInputData;

/**
 * Removing and updating a controller for the facade.
 */
public class FacadeRemoveAndUpdateController {
    private final WatchedListRemoveInputBoundary watchedListRemoveInputBoundary;
    private final GetWatchedListInputBoundary getWatchedListInputBoundary;

    public FacadeRemoveAndUpdateController(WatchedListRemoveInputBoundary watchedListRemoveInputBoundary,
                                           GetWatchedListInputBoundary getWatchedListInputBoundary) {
        this.watchedListRemoveInputBoundary = watchedListRemoveInputBoundary;
        this.getWatchedListInputBoundary = getWatchedListInputBoundary;
    }

    /**
     * Uses the facade pattern to remove and updated the watched list.
     * @param username the users' username.
     * @param title the movie title.
     */
    public void removeAndUpdateWatchedList(String username, String title) {
        final WatchedListRemoveInputData watchedListRemoveInputData = new WatchedListRemoveInputData(username, title);
        final GetWatchedListInputData getWatchedListInputData = new GetWatchedListInputData(username);
        watchedListRemoveInputBoundary.execute(watchedListRemoveInputData);
        getWatchedListInputBoundary.execute(getWatchedListInputData);
    }

    public WatchedListRemoveInputBoundary getWatchedListRemoveInputBoundary() {
        return watchedListRemoveInputBoundary;
    }

    public GetWatchedListInputBoundary getGetWatchedListInputBoundary() {
        return getWatchedListInputBoundary;
    }
}
