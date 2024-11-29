package use_case.rate;

/**
 * The output boundary for the rate use case.
 */
public interface RateOutputBoundary {

    /**
     * Prepares the success view for the rate use case.
     * @param outputData the output data of the rate use case.
     */
    void prepareSuccessView(RateOutputData outputData);

    /**
     * Prepares the fail view for the rate use case.
     * @param errorMessage the error message displayed when failing.
     */
    void prepareFailView(String errorMessage);

}
