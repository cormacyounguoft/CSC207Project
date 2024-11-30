
package use_case.export_watchlist;

/**
 * The input data for the Export Watchlist Use Case.
 */
public class ExportWatchlistInputData {
    private final String userId;

    public ExportWatchlistInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
