package use_case.rate;

import entity.Movie;

public class RateOutputData {

    private final String username;
    private final String title;
    private final boolean useCaseFailed;

    public RateOutputData(String username, String title, boolean useCaseFailed) {
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
