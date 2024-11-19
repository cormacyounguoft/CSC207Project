package use_case.go_to_rate;

public class GoToRateInputData {
    private final String username;
    private final String movie;
    public GoToRateInputData(String username, String movie) {
        this.username = username;
        this.movie = movie;
    }
    public String getUsername() {
        return username;
    }
    public String getMovie() {
        return movie;
    }

}
