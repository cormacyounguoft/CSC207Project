package use_case.go_to_rate;

/**
 * Output boundary for go rate use case.
 */
public interface GoRateOutputBoundary {

    /**
     * Switch to rate view.
     * @param rateOutputData output data for go rate use case.
     */
    void switchToRateView(GoRateOutputData rateOutputData);

}
