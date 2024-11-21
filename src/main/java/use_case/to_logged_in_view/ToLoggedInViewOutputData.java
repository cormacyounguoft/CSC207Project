package use_case.to_logged_in_view;

public class ToLoggedInViewOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public ToLoggedInViewOutputData(String username, boolean useCaseFailed) {
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
