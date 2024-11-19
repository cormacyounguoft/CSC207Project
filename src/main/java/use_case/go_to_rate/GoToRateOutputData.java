package use_case.go_to_rate;

public class GoToRateOutputData {
    private final String username;
    private final String movie;
    private final boolean useCaseFailed;
    public GoToRateOutputData(String username, String movie, boolean useCaseFailed) {
        this.username = username;
        this.movie = movie;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getMovie() {
        return movie;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
