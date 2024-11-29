package use_case.go_to_rate;

/**
 * Input data for go rate use_case.
 */
public class GoRateInputData {
    private final String username;
    private final String title;

    public GoRateInputData(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }
}
