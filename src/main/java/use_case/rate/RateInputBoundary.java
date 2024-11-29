package use_case.rate;

/**
 * Input Boundary for rate.
 */
public interface RateInputBoundary {

    /**
     * Executes the rate use case.
     * @param inputData the input data
     */
    void execute(RateInputData inputData);
}
