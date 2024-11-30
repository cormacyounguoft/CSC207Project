package use_case.export_watchedlist;

public class ExportWatchedListInputData {
    private final String userId;

    public ExportWatchedListInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
