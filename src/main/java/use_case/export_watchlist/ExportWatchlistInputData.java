
package use_case.export_watchlist;

public class ExportWatchlistInputData {
    private final String userId;
    public ExportWatchlistInputData(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
}
