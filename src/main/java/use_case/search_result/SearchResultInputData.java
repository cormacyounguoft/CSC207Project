package use_case.search_result;

import entity.Movie;

/**
 * The Input Data for the Search Result Use Case.
 */
public class SearchResultInputData {
    private final Movie movie;

    public SearchResultInputData(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
