package use_case.watched_list;

import use_case.logged_in_search_result.LoggedInSearchResultInputData;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;

public class WatchedListInteractor implements WatchedListInputBoundary {
    private final WatchedListOutputBoundary presenter;

    public WatchedListInteractor(WatchedListOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(WatchedListInputData inputData) {
        final WatchedListOutputData outputData = new WatchedListOutputData(inputData.getUsername(), inputData.getWatchedList(),
                false);
        presenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToLoggedInView(WatchedListInputData inputData) {
        final WatchedListOutputData outputData = new WatchedListOutputData(inputData.getUsername(), inputData.getWatchedList(),
                false);
        presenter.switchToLoggedInView(outputData);
    }

//    @Override
//    public void switchToRateView(LoggedInSearchResultInputData inputData) {
//        final LoggedInSearchResultOutputData outputData = new LoggedInSearchResultOutputData(
//                inputData.getUsername(), inputData.getMovie(), false);
//        presenter.switchToRateView(outputData);
//    }
}
