package use_case.watchlist;

public class WatchlistInteractor implements WatchlistInputBoundary {
    private final WatchlistOutputBoundary presenter;

    public WatchlistInteractor(WatchlistOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(WatchlistInputData inputData) {
        final WatchlistOutputData outputData = new WatchlistOutputData(inputData.getUsername(),
                inputData.getWatchlistTitle(), inputData.getWatchlistURL(), false);
        presenter.prepareSuccessView(outputData);
    }
}
