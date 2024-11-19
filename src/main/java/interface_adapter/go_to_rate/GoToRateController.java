package interface_adapter.go_to_rate;

import entity.Movie;
import use_case.go_to_rate.GoToRateInputBoundary;
import use_case.go_to_rate.GoToRateInputData;

public class GoToRateController {
    private final GoToRateInputBoundary interactor;
    public GoToRateController(GoToRateInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void goToRate(String username, String movie) {
        GoToRateInputData inputData = new GoToRateInputData(username, movie);
        interactor.switchToRateView(inputData);
    }
}
