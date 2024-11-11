package use_case.get_watchlist;

import entity.MovieList;

public class GetWatchlistInteractor implements GetWatchlistInputBoundary {
    private final GetWatchlistOutputBoundary presenter;
    private final GetWatchlistDataAccessInterface dataAccess;

    public GetWatchlistInteractor(GetWatchlistOutputBoundary presenter, GetWatchlistDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(GetWatchlistInputData inputData) {
        final MovieList watchlist = dataAccess.getWatchlist(inputData.getUsername());
        final GetWatchlistOutputData outputData = new GetWatchlistOutputData(inputData.getUsername(),
                watchlist, false);
        presenter.prepareSuccessView(outputData);
    }
}
