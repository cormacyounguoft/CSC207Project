package use_case.logged_in_search_result;

import entity.Movie;

/**
 * Output Data for the Logged In Search Result Use Case.
 */
public class LoggedInSearchResultOutputData {
    private final String username;
    private final Movie movie;
    private final boolean useCaseFailed;

    public LoggedInSearchResultOutputData(String username, Movie movie, boolean useCaseFailed) {
        this.username = username;
        this.movie = movie;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
