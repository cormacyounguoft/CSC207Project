package use_case.watchlistremove;

public class watchlistremoveInteractor  implements WatchlistremoveInputBoundary{
    private final watchlistremoveOutputBoundary presenter;
    private final WatchlistremoveDataAccessInterface useraccess;

    public watchlistremoveInteractor(watchlistremoveOutputBoundary presenter, WatchlistremoveDataAccessInterface useraccess){
        this.presenter = presenter;
        this.useraccess = useraccess;
    }

    @Override
    public void execute(watchlistremoveInputData inputData) {
        useraccess.removeMovieWatchlist(inputData.getUsername(), inputData.getWatchlistremoveTitle());
        presenter.prepareSuccessView();
    }
}
