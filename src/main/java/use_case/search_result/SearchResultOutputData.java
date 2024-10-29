package use_case.search_result;

import entity.Movie;

/**
 * Output Data for the Search Result Use Case.
 */
public class SearchResultOutputData {

    private final Movie movie;
    private final boolean useCaseFailed;

    public SearchResultOutputData(Movie movie, boolean useCaseFailed) {
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
