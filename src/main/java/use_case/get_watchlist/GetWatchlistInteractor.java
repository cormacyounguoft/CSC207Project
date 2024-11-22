package use_case.get_watchlist;

import entity.Movie;
import entity.MovieList;

import java.util.ArrayList;
import java.util.List;

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
        List<String> watchlistTitle = new ArrayList<>();
        List<String> watchlistURL = new ArrayList<>();
        for (Movie movie : watchlist.getMovieList()) {
            watchlistTitle.add(movie.getTitle());
            watchlistURL.add(movie.getPosterLink());
        }
        final GetWatchlistOutputData outputData = new GetWatchlistOutputData(inputData.getUsername(),
                watchlistTitle, watchlistURL, false);
        presenter.prepareSuccessView(outputData);
    }
}
