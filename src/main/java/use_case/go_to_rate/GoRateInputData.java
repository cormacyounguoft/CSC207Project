package use_case.go_to_rate;


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
