
package interface_adapter.export_watchlist;

import use_case.export_watchlist.ExportWatchlistInputBoundary;
import use_case.export_watchlist.ExportWatchlistInputData;

public class ExportWatchlistController {
    private final ExportWatchlistInputBoundary interactor;

    public ExportWatchlistController(ExportWatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void exportWatchlist(String userId) {
        ExportWatchlistInputData inputData = new ExportWatchlistInputData(userId);
        interactor.exportWatchlist(inputData);
    }
}
