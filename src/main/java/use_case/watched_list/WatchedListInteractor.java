package use_case.watched_list;

import entity.Movie;
import use_case.logged_in_search_result.LoggedInSearchResultInputData;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;

import java.util.ArrayList;
import java.util.List;

public class WatchedListInteractor implements WatchedListInputBoundary {
    private final WatchedListOutputBoundary presenter;

    public WatchedListInteractor(WatchedListOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(WatchedListInputData inputData) {
        final WatchedListOutputData outputData = new WatchedListOutputData(inputData.getUsername(),
                inputData.getWatchedListTitle(), inputData.getWatchedListURL(), false);
        presenter.prepareSuccessView(outputData);
    }
}
