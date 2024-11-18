package use_case.search_result;

import entity.Movie;

/**
 * The Input Data for the Search Result Use Case.
 */
public class SearchResultInputData {
    private final String movie;

    public SearchResultInputData(String movie) {
        this.movie = movie;
    }

    public String getMovie() {
        return movie;
    }
}
