package use_case.rated_list;

/**
 * The rated list output boundary for the rated list use case.
 */
public interface RatedListOutputBoundary {
    /**
     * Prepares the success view for the rated list.
     * @param RateOutputData the output data from the rated list.
     */
    void prepareSuccessView(RatedListOutputData RateOutputData);
}
