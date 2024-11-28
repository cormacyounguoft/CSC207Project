package use_case.go_to_rate;

public class GoRateOutputData {
    private final String username;
    private final String title;
    private final boolean useCaseFailed;
    public GoRateOutputData(String username, String title, boolean useCaseFailed) {
        this.username = username;
        this.title = title;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getTitle() {
        return title;
    }
}
