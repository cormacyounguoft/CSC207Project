
package interface_adapter.export_watchlist;

import use_case.export_watchlist.ExportWatchlistOutputBoundary;
import use_case.export_watchlist.ExportWatchlistOutputData;

public class ExportWatchlistPresenter implements ExportWatchlistOutputBoundary {

    @Override
    public void presentExportResult(ExportWatchlistOutputData outputData) {
        if (outputData.isSuccess()) {
            System.out.println("Watchlist exported successfully to " + outputData.getFilePath());
        } else {
            System.out.println("Failed to export the watchlist.");
        }
    }
}
