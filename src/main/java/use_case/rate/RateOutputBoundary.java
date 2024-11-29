package use_case.rate;

/**
 * Output Boundary for rate.
 */
public interface RateOutputBoundary {

    /**
     * Prepares the success view for the rate Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RateOutputData outputData);

    /**
     * Prepares the fail view for the rate Use Case.
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);
}
