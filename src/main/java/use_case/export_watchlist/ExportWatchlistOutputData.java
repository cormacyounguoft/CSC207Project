
package use_case.export_watchlist;

/**
 * The output data for the Export Watchlist Use Case.
 */
public class ExportWatchlistOutputData {
    private final boolean success;
    private final String filePath;
    private final String username;

    public ExportWatchlistOutputData(boolean success, String filePath, String username) {
        this.success = success;
        this.filePath = filePath;
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getUsername() {
        return username;
    }
}
