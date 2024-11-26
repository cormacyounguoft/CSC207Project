package use_case.dashboard;
import use_case.logged_in.LoggedInOutputData;

public interface DashboardOutputBoundary {

    /**
     * Prepares the success view for the Dashboard Use Case.
     *
     * @param outputData the processed output data for the dashboard
     */
    void prepareSuccessView(DashboardOutputData outputData);
}
