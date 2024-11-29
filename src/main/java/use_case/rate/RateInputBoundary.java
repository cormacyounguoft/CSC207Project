package use_case.rate;

/**
 * Input Boundary for the rate use case.
 */
public interface RateInputBoundary {

    /**
     * Execute the input boundary for the rate use case with the input data.
     * @param inputData the input data for the rate use case.
     */
    void execute(RateInputData inputData);

}
