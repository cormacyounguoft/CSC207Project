package interface_adapter.logged_in_search_result;

import entity.Movie;

/**
 * The state for the Logged In Search Result View Model.
 */
public class LoggedInSearchResultState {
    private String username;
    private Movie movie;
    private String movieError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    public void setResult(String username, Movie movie) {
        this.username = username;
        this.movie = movie;
    }
}
