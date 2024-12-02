package interface_adapter.export_watchlist;

import use_case.export_watchlist.ExportWatchlistInputBoundary;
import use_case.export_watchlist.ExportWatchlistInputData;

/**
 * The controller for the export watchlist use case.
 */
public class ExportWatchlistController {

    private final ExportWatchlistInputBoundary interactor;

    public ExportWatchlistController(ExportWatchlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the export Controller.
     * @param userId username of the User.
     */
    public void execute(String userId) {
        final ExportWatchlistInputData inputData = new ExportWatchlistInputData(userId);
        interactor.exportWatchlist(inputData);
    }
}
