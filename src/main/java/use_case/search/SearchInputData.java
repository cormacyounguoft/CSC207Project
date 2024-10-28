package use_case.search;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {
    private String searchQuery;

    public SearchInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
