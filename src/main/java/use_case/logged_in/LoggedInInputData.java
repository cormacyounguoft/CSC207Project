package use_case.logged_in;

/**
 * The input data for the Logged In Use Case.
 */
public class LoggedInInputData {

    private final String username;

    public LoggedInInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
