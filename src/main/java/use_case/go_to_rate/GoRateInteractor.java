package use_case.go_to_rate;

/**
 * Interactor for go to rate use case.
 */
public class GoRateInteractor implements GoRateInputBoundary {
    private final GoRateOutputBoundary presenter;

    public GoRateInteractor(GoRateOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void switchToRateView(GoRateInputData inputData) {
        final GoRateOutputData outputData = new GoRateOutputData(inputData.getUsername(), inputData.getTitle(), false);
        presenter.switchToRateView(outputData);
    }
}
