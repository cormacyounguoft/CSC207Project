package interface_adapter.go_to_rate;

import entity.Movie;
import use_case.go_to_rate.GoRateInputBoundary;
import use_case.go_to_rate.GoRateInputData;

public class GoRateController {
    private final GoRateInputBoundary interactor;
    public GoRateController(GoRateInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void goToRate(String username, Movie movie) {
        GoRateInputData inputData = new GoRateInputData(username, movie);
        interactor.switchToRateView(inputData);
    }
}
