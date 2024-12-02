package use_case.get_watchlist;

import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import use_case.MovieListDataAccessInterface;

/**
 * Interactor for get watch list.
 */
public class GetWatchlistInteractor implements GetWatchlistInputBoundary {
    private final GetWatchlistOutputBoundary presenter;
    private final MovieListDataAccessInterface dataAccess;

    public GetWatchlistInteractor(GetWatchlistOutputBoundary presenter, MovieListDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(GetWatchlistInputData inputData) {
        final List<String> watchlistTitle = new ArrayList<>();
        final List<String> watchlistUrl = new ArrayList<>();
        for (Movie movie : dataAccess.getWatchlist(inputData.getUsername()).getMovieList()) {
            watchlistTitle.add(movie.getTitle());
            watchlistUrl.add(movie.getPosterLink());
        }
        final GetWatchlistOutputData outputData = new GetWatchlistOutputData(inputData.getUsername(),
                watchlistTitle, watchlistUrl, false);
        presenter.prepareSuccessView(outputData);
    }
}
