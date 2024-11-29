package use_case.go_to_rate;

/**
 * Input boundary for go rate use case.
 */
public interface GoRateInputBoundary {

    /**
     * Switch to rate view.
     * @param goRateInputData input data for go rate use case.
     */
    void switchToRateView(GoRateInputData goRateInputData);

}
