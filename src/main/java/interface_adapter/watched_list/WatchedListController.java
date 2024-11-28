package interface_adapter.watched_list;

import entity.MovieList;
import use_case.watched_list.WatchedListInputBoundary;
import use_case.watched_list.WatchedListInputData;

import java.util.List;

public class WatchedListController {
    private final WatchedListInputBoundary interactor;

    public WatchedListController(WatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, String title) {
        final WatchedListInputData inputData = new WatchedListInputData(username, title);
        interactor.execute(inputData);
    }
}
