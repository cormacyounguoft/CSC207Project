
package use_case.export_watchlist;

public class ExportWatchlistOutputData {
    private final boolean success;
    private final String filePath;

    public ExportWatchlistOutputData(boolean success, String filePath) {
        this.success = success;
        this.filePath = filePath;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFilePath() {
        return filePath;
    }
}
