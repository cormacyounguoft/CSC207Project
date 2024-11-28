package use_case.watched_list;

import entity.Movie;
import use_case.SearchDataAccessInterface;
import use_case.logged_in_search_result.LoggedInSearchResultInputData;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WatchedListInteractor implements WatchedListInputBoundary {
    private final WatchedListOutputBoundary presenter;
    private final WatchedListUserDataAccessInterface dataAccessInterface;

    public WatchedListInteractor(WatchedListOutputBoundary presenter, WatchedListUserDataAccessInterface dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(WatchedListInputData inputData) {
        dataAccessInterface.removeFromWatchedlist(inputData.getUsername(), inputData.getTitle());
        final WatchedListOutputData outputData = new WatchedListOutputData(inputData.getUsername());
        presenter.prepareSuccessView(outputData);

    }
}
