package interface_adapter.dashboard;

import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;

/**
 * The controller for the dashboard of the application.
 */
public class DashboardController {

    private final DashboardInputBoundary interactor;

    public DashboardController(DashboardInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the dashboard Controller.
     * @param username username of the User.
     */
    public void execute(String username) {
        final DashboardInputData inputData = new DashboardInputData(username);
        interactor.execute(inputData);
    }

}
