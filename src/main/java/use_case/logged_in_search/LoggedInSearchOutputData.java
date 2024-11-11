package use_case.logged_in_search;

import entity.Movie;

/**
 * Output Data for the Logged In Search Use Case.
 */
public class LoggedInSearchOutputData {
    private final String username;
    private final Movie movie;
    private final boolean useCaseFailed;

    public LoggedInSearchOutputData(String username, Movie movie, boolean useCaseFailed) {
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
