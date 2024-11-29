package use_case.logged_in_search;

import java.io.IOException;

import entity.Movie;
import use_case.SearchDataAccessInterface;

/**
 * The Logged In Search Interactor.
 */
public class LoggedInSearchInteractor implements LoggedInSearchInputBoundary {

    private final LoggedInSearchOutputBoundary presenter;
    private final SearchDataAccessInterface searchDataAccessObject;

    public LoggedInSearchInteractor(LoggedInSearchOutputBoundary loggedInSearchOutputBoundary,
                                    SearchDataAccessInterface searchDataAccessObject) {
        this.presenter = loggedInSearchOutputBoundary;
        this.searchDataAccessObject = searchDataAccessObject;
    }

    @Override
    public void execute(LoggedInSearchInputData loggedInSearchInputData) {
        try {
            final Movie movie = searchDataAccessObject.search(loggedInSearchInputData.getSearchQuery());
            final String username = loggedInSearchInputData.getUsername();
            final LoggedInSearchOutputData loggedInSearchOutputData = new LoggedInSearchOutputData(username,
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
            presenter.prepareSuccessView(loggedInSearchOutputData);
        }
        catch (IOException exception) {
            presenter.prepareFailView("Movie not found!");
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
        String result = "Rotten tomatoes not available.";
        if (movie.getRottenTomatoes() != -1) {
            result = String.valueOf(movie.getRottenTomatoes());
        }
        return result;
    }

    private String getMovieGenre(Movie movie) {
        String result = "Genre not available.";
        if (!movie.getGenre().isEmpty()) {
            result = movie.getGenre().toString();
        }
        return result;
    }

    private String getMovieActors(Movie movie) {
        String result = "Actor not available.";
        if (!movie.getActors().isEmpty()) {
            result = movie.getActors().toString();
        }
        return result;
    }

    private String getMovieDirector(Movie movie) {
        String result = "Director not available.";
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
