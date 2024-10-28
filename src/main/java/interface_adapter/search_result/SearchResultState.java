package interface_adapter.search_result;

import entity.Movie;


/**
 * The state for the Search Result View Model.
 */
public class SearchResultState {
    private Movie result;
    private String movieError;

    public Movie getResult() {
        return result;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    public void setResult(Movie result) {
        this.result = result;
    }
}