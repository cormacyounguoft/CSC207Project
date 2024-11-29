package use_case.add_to_watched_list;

/**
 * Output Data for the add to watched list Use Case.
 */
public class AddToWatchedListOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public AddToWatchedListOutputData(String username, boolean useCaseFailed) {
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
