package use_case.go_to_rate;

/**
 * Output boundary for go to rate use case.
 */
public interface GoRateOutputBoundary {

    /**
     * Executes the switch to rate view use case.
     * @param rateOutputData output data for go rate use case.
     */
    void switchToRateView(GoRateOutputData rateOutputData);

}
