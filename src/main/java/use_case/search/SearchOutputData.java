package use_case.search;

import entity.Movie;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final Movie movie;
    private final boolean useCaseFailed;

    public SearchOutputData(Movie movie, boolean useCaseFailed) {
        this.movie = movie;
        this.useCaseFailed = useCaseFailed;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
