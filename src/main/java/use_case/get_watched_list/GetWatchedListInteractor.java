package use_case.get_watched_list;

import entity.Movie;
import entity.MovieList;
import use_case.SearchDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class GetWatchedListInteractor implements GetWatchedListInputBoundary {
    private final GetWatchedListOutputBoundary presenter;
    private final GetWatchedListDataAccessInterface dataAccess;

    public GetWatchedListInteractor(GetWatchedListOutputBoundary presenter, GetWatchedListDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }


    @Override
    public void execute(GetWatchedListInputData inputData) {
        final MovieList watchedList = dataAccess.getWatchedList(inputData.getUsername());
        List<String> watchedListTitle = new ArrayList<>();
        List<String> watchedListURL = new ArrayList<>();
        for (Movie movie : watchedList.getMovieList()) {
            watchedListTitle.add(movie.getTitle());
            watchedListURL.add(movie.getPosterLink());
        }
        final GetWatchedListOutputData outputData = new GetWatchedListOutputData(inputData.getUsername(),
                watchedListTitle, watchedListURL, false);
        presenter.prepareSuccessView(outputData);
    }
}
