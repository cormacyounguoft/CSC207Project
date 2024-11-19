package use_case.go_to_rate;


public class GoToRateInteractor implements GoToRateInputBoundary {
    private final GoToRateOutputBoundary presenter;
    public GoToRateInteractor(GoToRateOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void switchToRateView(GoToRateInputData inputData) {
        final GoToRateOutputData outputData = new GoToRateOutputData(inputData.getUsername(), inputData.getMovie(), false);
        presenter.switchToRateView(outputData);
    }
}
