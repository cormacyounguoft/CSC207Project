package use_case.watched_list_remove;

public class WatchedListRemoveInteractor implements WatchedListRemoveInputBoundary {
    private final WatchedListRemoveOutputBoundary presenter;
    private final WatchedListRemoveDataAccessInterface dataAccessInterface;

    public WatchedListRemoveInteractor(WatchedListRemoveOutputBoundary presenter, WatchedListRemoveDataAccessInterface dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(WatchedListRemoveInputData inputData) {
        dataAccessInterface.removeFromWatchedlist(inputData.getUsername(), inputData.getTitle());
        final WatchedListRemoveOutputData outputData = new WatchedListRemoveOutputData(inputData.getUsername(), false);
        presenter.prepareSuccessView(outputData);

    }
}
