package use_case.search;

import java.io.IOException;

import entity.Movie;
import entity.MovieFactory;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface searchDataAccessObject;
    private final SearchOutputBoundary searchPresenter;
    private final MovieFactory movieFactory;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary,
                            MovieFactory movieFactory) {
        this.searchDataAccessObject = searchDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
        this.movieFactory = movieFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        try {
            final Movie movie = searchDataAccessObject.search(searchInputData.getSearchQuery());

            final SearchOutputData searchOutputData = new SearchOutputData(movie, false);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
        catch (IOException exception) {
            searchPresenter.prepareFailView("Movie not found!");
        }
    }
}
