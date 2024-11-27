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
                    getMovieinfo("title", movie),
                    getMovieinfo("release_date", movie),
                    getMovieinfo("description", movie),
                    getMovieinfo("rottentomatoes", movie),
                    getMovieinfo("runtime", movie),
                    getMovieinfo("genre", movie),
                    getMovieinfo("actors", movie),
                    getMovieinfo("director", movie),
                    getMovieinfo("poster", movie),
                    false);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
        catch (IOException exception) {
            searchPresenter.prepareFailView("Movie not found!");
        }


    }
    private String getMovieinfo(String info, Movie movie){
        String result;

        switch (info.toLowerCase()) {
            case "release_date":
                result = movie.getReleaseDate().isEmpty() ? "Release date not available." : movie.getReleaseDate();
                break;
            case "description":
                result = movie.getDescription().isEmpty() ? "Description not available." : movie.getDescription();
                break;
            case "rottentomatoes":
                result = movie.getRottenTomatoes() == -1 ? "Rotten Tomatoes not available." : String.valueOf(movie.getRottenTomatoes());
                break;
            case "runtime":
                result = movie.getRuntime() == -1 ? "Runtime not available." : String.valueOf(movie.getRuntime());
                break;
            case "genre":
                result = movie.getGenre().isEmpty() ? "Genres not available." : String.valueOf(movie.getGenre());
                break;
            case "actors":
                result = movie.getActors().isEmpty() ? "Actors not available." : String.valueOf(movie.getActors());
                break;
            case "director":
                result = movie.getDirector().isEmpty() ? "Directors not available." : String.valueOf(movie.getDirector());
                break;
            case "poster":
                result = movie.getPosterLink().isEmpty() ? "Poster not available." : movie.getPosterLink();
                break;
            default:
                result = movie.getTitle();

        }

        return result;
    }
}
