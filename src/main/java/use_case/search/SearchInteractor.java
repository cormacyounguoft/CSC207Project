package use_case.search;

import java.io.IOException;

import entity.Movie;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface searchDataAccessObject;
    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
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

    public String getMovieReleaseDate(Movie movie) {
        if (movie.getReleaseDate().isEmpty()) {
            return "Release date not available.";
        }
        else {
            return movie.getReleaseDate();
        }
    }

    public String getMovieDescription(Movie movie) {
        if (movie.getDescription().isEmpty()) {
            return "Description not available.";
        }
        else {
            return movie.getDescription();
        }
    }

    public String getMovieRottenTomatoes(Movie movie) {
        if (movie.getRottenTomatoes() == -1) {
            return "Rotten Tomatoes not available.";
        }
        else {
            return String.valueOf(movie.getRottenTomatoes());
        }
    }

    public String getMovieGenre(Movie movie) {
        if (movie.getGenre().isEmpty()) {
            return "Genres not available.";
        }
        else {
            return movie.getGenre().toString();
        }
    }

    public String getMovieActors(Movie movie) {
        if (movie.getActors().isEmpty()) {
            return "Actors not available.";
        }
        else {
            return movie.getActors().toString();
        }
    }

    public String getMovieDirector(Movie movie) {
        if (movie.getDirector().isEmpty()) {
            return "Directors not available.";
        }
        else {
            return movie.getDirector().toString();
        }
    }

    public String getMovieRuntime(Movie movie) {
        if (movie.getRuntime() == -1) {
            return "Runtime not available.";
        }
        else {
            return String.valueOf(movie.getRuntime());
        }
    }

    public String getMoviePoster(Movie movie) {
        if (movie.getPosterLink().isEmpty()) {
            return "Poster not available.";
        }
        else {
            return movie.getPosterLink();
        }
    }
}
