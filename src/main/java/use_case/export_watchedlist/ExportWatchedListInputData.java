package use_case.export_watchedlist;

/**
 * The input data for the Export Watched List Use Case.
 */
public class ExportWatchedListInputData {
    private final String userId;

    public ExportWatchedListInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
