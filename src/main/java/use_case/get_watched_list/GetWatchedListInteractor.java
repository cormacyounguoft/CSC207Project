package use_case.get_watched_list;

import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import entity.MovieList;

/**
 * Interactor for get watched list use case.
 */
public class GetWatchedListInteractor implements GetWatchedListInputBoundary {
    private final GetWatchedListOutputBoundary presenter;
    private final GetWatchedListDataAccessInterface dataAccess;

    public GetWatchedListInteractor(GetWatchedListOutputBoundary presenter,
                                    GetWatchedListDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(GetWatchedListInputData inputData) {
        final MovieList watchedList = dataAccess.getWatchedList(inputData.getUsername());
        final List<String> watchedListTitle = new ArrayList<>();
        final List<String> watchedListUrl = new ArrayList<>();
        for (Movie movie : watchedList.getMovieList()) {
            watchedListTitle.add(movie.getTitle());
            watchedListUrl.add(movie.getPosterLink());
        }
        final GetWatchedListOutputData outputData = new GetWatchedListOutputData(inputData.getUsername(),
                watchedListTitle, watchedListUrl, false);
        presenter.prepareSuccessView(outputData);
    }
}
