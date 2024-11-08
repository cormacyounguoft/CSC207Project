package use_case.logged_in_search_result;

import entity.Movie;

/**
 * The Input Data for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultInputData {
    private String username;
    private final Movie movie;

    public LoggedInSearchResultInputData(String username, Movie movie) {
        this.username = username;
        this.movie = movie;
    }

    public String getUsername() {
        return username;
    }

    public Movie getMovie() {
        return movie;
    }
}
