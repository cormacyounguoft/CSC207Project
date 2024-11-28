package use_case.dashboard;

/**
 * The Output Boundary for the Dashboard.
 */
public interface DashboardOutputBoundary {

    /**
     * Prepares the success view for the Dashboard Use Case.
     *
     * @param outputData the processed output data for the dashboard
     */
    void prepareSuccessView(DashboardOutputData outputData);
}
