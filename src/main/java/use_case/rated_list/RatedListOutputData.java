package use_case.rated_list;

/**
 * The output data for the rated list use case.
 */
public class RatedListOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public RatedListOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
