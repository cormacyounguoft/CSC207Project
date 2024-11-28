package use_case.watchlistremove;

public class watchlistremoveOutputData {
    private boolean success;

    public watchlistremoveOutputData(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
