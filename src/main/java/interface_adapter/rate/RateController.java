package interface_adapter.rate;

import entity.Movie;
import use_case.rate.RateInputBoundary;
import use_case.rate.RateInputData;

public class RateController {
    private final RateInputBoundary useCaseInteractor;

    public RateController(RateInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void execute(String username, String movie, int rating) {
        final RateInputData inputData = new RateInputData(username, movie, rating);
        useCaseInteractor.execute(inputData);
    }
}
