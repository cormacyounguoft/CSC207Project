package use_case.rate;

/**
 * The interactor for the rate function.
 */
public class RateInteractor implements RateInputBoundary {
    private static final int MAX_RATING = 5;
    private final RateUserDataAccessInterface userDataAccessInterface;
    private final RateOutputBoundary presenter;

    public RateInteractor(RateUserDataAccessInterface userDataAccessInterface, RateOutputBoundary presenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(RateInputData inputData) {
        if (inputData.getRating() > MAX_RATING) {
            presenter.prepareFailView("Rating must be between 0 and 5 inclusive.");
        }
        else if (inputData.getRating() < 0) {
            presenter.prepareFailView("Rating must be between 0 and 5 inclusive.");
        }
        else {
            userDataAccessInterface.saveUserRating(inputData.getUsername(), inputData.getTitle(),
                    inputData.getRating());
            final RateOutputData outputData = new RateOutputData(inputData.getUsername(),
                    inputData.getTitle(), false);
            presenter.prepareSuccessView(outputData);
        }
    }

}
