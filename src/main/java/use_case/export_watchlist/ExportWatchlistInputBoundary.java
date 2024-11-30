
package use_case.export_watchlist;

/**
 * The input boundary for the Export Watchlist Use Case.
 */
public interface ExportWatchlistInputBoundary {

    /**
     * Exports the watchlist of the user.
     * @param inputData the input data of the watchlist.
     */
    void exportWatchlist(ExportWatchlistInputData inputData);
}
