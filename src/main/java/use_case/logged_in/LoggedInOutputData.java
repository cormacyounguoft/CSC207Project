package use_case.logged_in;

/**
 * Output Data for the Logged In Use Case.
 */
public class LoggedInOutputData {

    private final boolean useCaseFailed;
    private final String username;

    public LoggedInOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
