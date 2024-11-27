package use_case.dashboard;

public interface DashboardInputBoundary {
    /**
     * Executes the Dashboard Use Case to aggregate and process data.
     *
     * @param inputData the input data containing the username
     */
    void execute(DashboardInputData inputData);
}
