package interface_adapter.facade_watchedlist;

import use_case.get_watched_list.GetWatchedListInputBoundary;
import use_case.get_watched_list.GetWatchedListInputData;
import use_case.watched_list_remove.WatchedListRemoveInputBoundary;
import use_case.watched_list_remove.WatchedListRemoveInputData;

public class FacadeRemoveAndUpdateController {
    WatchedListRemoveInputBoundary watchedListRemoveInputBoundary;
    GetWatchedListInputBoundary getWatchedListInputBoundary;

    public FacadeRemoveAndUpdateController(WatchedListRemoveInputBoundary watchedListRemoveInputBoundary, GetWatchedListInputBoundary getWatchedListInputBoundary) {
        this.watchedListRemoveInputBoundary = watchedListRemoveInputBoundary;
        this.getWatchedListInputBoundary = getWatchedListInputBoundary;
    }

    public void removeAndUpdateWatchedList(String username, String title) {
        final WatchedListRemoveInputData watchedListRemoveInputData = new WatchedListRemoveInputData(username, title);
        final GetWatchedListInputData getWatchedListInputData = new GetWatchedListInputData(username);
        watchedListRemoveInputBoundary.execute(watchedListRemoveInputData);
        getWatchedListInputBoundary.execute(getWatchedListInputData);
    }
}

