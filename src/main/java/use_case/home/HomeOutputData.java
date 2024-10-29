package use_case.home;

/**
 * Output Data for the Home Use Case.
 */
public class HomeOutputData {
    private final boolean useCaseFailed;

    public HomeOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
