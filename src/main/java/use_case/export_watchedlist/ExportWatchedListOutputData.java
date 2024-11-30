package use_case.export_watchedlist;

/**
 * Output data for the Export WatchedList output data.
 */
public class ExportWatchedListOutputData {
    private final boolean success;
    private final String filePath;
    private final String userId;

    public ExportWatchedListOutputData(boolean success, String filePath, String userId) {
        this.success = success;
        this.filePath = filePath;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getUserId() {
        return userId;
    }
}
