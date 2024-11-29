package use_case.search;

import java.io.IOException;

import entity.Movie;
import entity.MovieFactory;
import use_case.SearchDataAccessInterface;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {

    private final SearchDataAccessInterface searchDataAccessObject;
    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary,
                            MovieFactory movieFactory) {
        this.searchDataAccessObject = searchDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        try {
            final Movie movie = searchDataAccessObject.search(searchInputData.getSearchQuery());

            final SearchOutputData searchOutputData = new SearchOutputData(
                    movie.getTitle(),
                    getMovieReleaseDate(movie),
                    getMovieDescription(movie),
                    getMovieRottenTomatoes(movie),
                    getMovieRuntime(movie),
                    getMovieGenre(movie),
                    getMovieActors(movie),
                    getMovieDirector(movie),
                    getMoviePoster(movie),
                    false);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
        catch (IOException exception) {
            searchPresenter.prepareFailView("Movie not found!");
        }
    }

    private String getMovieReleaseDate(Movie movie) {
        String result = "Release date not available.";
        if (!movie.getReleaseDate().isEmpty()) {
            result = movie.getReleaseDate();
        }
        return result;
    }

    private String getMovieDescription(Movie movie) {
        String result = "Description not available.";
        if (!movie.getDescription().isEmpty()) {
            result = movie.getDescription();
        }
        return result;
    }

    private String getMovieRottenTomatoes(Movie movie) {
        String result = "Rotten Tomatoes not available.";
        if (movie.getRottenTomatoes() != -1) {
            result = String.valueOf(movie.getRottenTomatoes());
        }
        return result;
    }

    private String getMovieGenre(Movie movie) {
        String result = "Genres not available.";
        if (!movie.getGenre().isEmpty()) {
            result = movie.getGenre().toString();
        }
        return result;
    }

    private String getMovieActors(Movie movie) {
        String result = "Actors not available.";
        if (!movie.getActors().isEmpty()) {
            result = movie.getActors().toString();
        }
        return result;
    }

    private String getMovieDirector(Movie movie) {
        String result = "Directors not available.";
        if (!movie.getDirector().isEmpty()) {
            result = movie.getDirector().toString();
        }
        return result;
    }

    private String getMovieRuntime(Movie movie) {
        String result = "Runtime not available.";
        if (movie.getRuntime() != -1) {
            result = String.valueOf(movie.getRuntime());
        }
        return result;
    }

    private String getMoviePoster(Movie movie) {
        String result = "Poster not available.";
        if (!movie.getPosterLink().isEmpty()) {
            result = movie.getPosterLink();
        }
        return result;
    }
}
