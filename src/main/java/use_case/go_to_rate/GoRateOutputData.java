package use_case.go_to_rate;

import entity.Movie;
import entity.MovieList;

import java.util.Map;

public class GoRateOutputData {
    private final String username;
    private final Movie movie;
    private final boolean useCaseFailed;
    public GoRateOutputData(String username, Movie movie, boolean useCaseFailed) {
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
