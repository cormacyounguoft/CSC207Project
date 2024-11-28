package use_case.dashboard;

/**
 * The input data for the Dashboard.
 */
public class DashboardInputData {
    private final String username;

    public DashboardInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
