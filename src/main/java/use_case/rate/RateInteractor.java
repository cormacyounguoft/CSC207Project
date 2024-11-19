package use_case.rate;

import entity.Movie;
import use_case.SearchDataAccessInterface;

import java.io.IOException;

public class RateInteractor implements RateInputBoundary {
    private final RateUserDataAccessInterface userDataAccessInterface;
    private final SearchDataAccessInterface searchDataAccessInterface;
    private final RateOutputBoundary presenter;

    public RateInteractor(RateUserDataAccessInterface userDataAccessInterface,
                          SearchDataAccessInterface searchDataAccessInterface,
                          RateOutputBoundary presenter) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.searchDataAccessInterface = searchDataAccessInterface;
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
            try {
                Movie movie = searchDataAccessInterface.search(inputData.getMovie());
                userDataAccessInterface.saveUserRating(inputData.getUsername(), movie, inputData.getRating());
                final RateOutputData outputData = new RateOutputData(inputData.getUsername(), inputData.getMovie(), false);
                presenter.prepareSuccessView(outputData);
            }
            catch (IOException exception) {
                presenter.prepareFailView("Error");
            }
        }
    }
}
