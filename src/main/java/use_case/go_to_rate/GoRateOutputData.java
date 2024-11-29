package use_case.go_to_rate;

/**
 * Output data for go rate use case.
 */
public class GoRateOutputData {
    private final String username;
    private final String title;
    private final boolean useCaseFailed;

    /**
     * Output data for go rate use case.
     * @param username username of user executing go rate use case.
     * @param title title of movie being rated.
     * @param useCaseFailed if usecase is failed or not.
     */
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
