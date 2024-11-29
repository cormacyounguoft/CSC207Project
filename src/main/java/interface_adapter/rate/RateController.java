package interface_adapter.rate;

import use_case.rate.RateInputBoundary;
import use_case.rate.RateInputData;

/**
 * The controller for the Rate Use Case.
 */
public class RateController {

    private final RateInputBoundary useCaseInteractor;

    public RateController(RateInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * Execute the controller for the rate use case.
     * @param username the username of the user giving the rating.
     * @param title the title of the movie the rating is being assigned to.
     * @param rating the rating given to the movie by the user.
     */
    public void execute(String username, String title, int rating) {
        final RateInputData inputData = new RateInputData(username, title, rating);
        useCaseInteractor.execute(inputData);
    }
}
