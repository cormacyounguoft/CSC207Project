package interface_adapter.rate;

import entity.Movie;
import use_case.rate.RateInputBoundary;
import use_case.rate.RateInputData;

public class RateController {
    private final RateInputBoundary useCaseInteractor;

    public RateController(RateInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void switchToLoggedInView(String username) {
        final RateInputData inputData = new RateInputData(username, "", 0);
        useCaseInteractor.switchToLoggedInView(inputData);
    }

    public void execute(String username, String title, int rating) {
        final RateInputData inputData = new RateInputData(username, title, rating);
        useCaseInteractor.execute(inputData);
    }
}
