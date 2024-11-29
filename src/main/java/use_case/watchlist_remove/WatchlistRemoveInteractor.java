package use_case.watchlist_remove;

/**
 * Interactor for watchlist remove.
 */
public class WatchlistRemoveInteractor implements WatchlistRemoveInputBoundary {

    private final WatchlistRemoveOutputBoundary presenter;
    private final WatchlistRemoveDataAccessInterface dataAccessInterface;

    public WatchlistRemoveInteractor(WatchlistRemoveOutputBoundary presenter,
                                     WatchlistRemoveDataAccessInterface
            dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(WatchlistRemoveInputData inputData) {
        dataAccessInterface.removeFromWatchlist(inputData.getUsername(), inputData.getTitle());
        final WatchlistRemoveOutputData outputData =
                new WatchlistRemoveOutputData(false, inputData.getUsername());
        presenter.prepareSuccessView(outputData);
    }
}
