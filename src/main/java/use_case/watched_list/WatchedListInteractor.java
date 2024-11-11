package use_case.watched_list;

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
}
