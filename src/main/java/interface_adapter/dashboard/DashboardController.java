package interface_adapter.dashboard;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;
public class DashboardController {
    private final DashboardInputBoundary interactor;
    public DashboardController(DashboardInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(String username) {
        final DashboardInputData inputData = new DashboardInputData(username);
        interactor.execute(inputData);
    }
    public void switchToLoggedInView(String username) {
        final DashboardInputData inputData = new DashboardInputData(username);
        interactor.switchToLoggedInView(inputData);
    }
}