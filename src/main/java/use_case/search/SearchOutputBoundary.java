package use_case.search;

/**
 * The output boundary for the Search Use Case.
 */
public interface SearchOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchOutputData outputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
