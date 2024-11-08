package use_case.rate;

public class RateInteractor implements RateInputBoundary {
    private final RateUserDataAccessInterface userDataAccessInterface;
    private final RateOutputBoundary presenter;

    public RateInteractor(RateUserDataAccessInterface userDataAccessInterface, RateOutputBoundary presenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(RateInputData inputData) {
        if (inputData.getRating() > 5) {
            presenter.prepareFailView("Rating must be between 0 and 5 inclusive.");
        }
        else if (inputData.getRating() < 0) {
            presenter.prepareFailView("Rating must be between 0 and 5 inclusive.");
        }
        else {
            userDataAccessInterface.saveUserRating(inputData.getUsername(), inputData.getMovie(), inputData.getRating());
            final RateOutputData outputData = new RateOutputData(inputData.getUsername(), inputData.getMovie(), false);
            presenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToLoggedInView(RateInputData inputData) {
        final RateOutputData outputData = new RateOutputData(inputData.getUsername(), inputData.getMovie(), false);
        presenter.switchToLoggedInView(outputData);
    }
}