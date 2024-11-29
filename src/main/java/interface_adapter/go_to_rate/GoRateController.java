package interface_adapter.go_to_rate;

import use_case.go_to_rate.GoRateInputBoundary;
import use_case.go_to_rate.GoRateInputData;

/**
 * The controller for the goto rate use case.
 */
public class GoRateController {

    private final GoRateInputBoundary interactor;

    public GoRateController(GoRateInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the controller for the goto rate use case.
     * @param title the movie title.
     * @param username the username.
     */
    public void goToRate(String username, String title) {
        final GoRateInputData inputData = new GoRateInputData(username, title);
        interactor.switchToRateView(inputData);
    }
}
