package use_case.search_result;

/**
 * Output Data for the Search Result Use Case.
 */
public class SearchResultOutputData {

    private final boolean useCaseFailed;

    public SearchResultOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
