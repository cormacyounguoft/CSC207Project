package use_case.rate;

import entity.Movie;

public class RateOutputData {

    private final String username;
    private final Movie movie;
    private final boolean useCaseFailed;

    public RateOutputData(String username, Movie movie, boolean useCaseFailed) {
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
