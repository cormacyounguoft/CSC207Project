package use_case.go_to_rate;

/**
 * Input boundary for go to rate use case.
 */
public interface GoRateInputBoundary {

    /**
     * Executes the switch to rate view use case.
     * @param goRateInputData input data for go rate use case.
     */
    void switchToRateView(GoRateInputData goRateInputData);

}
