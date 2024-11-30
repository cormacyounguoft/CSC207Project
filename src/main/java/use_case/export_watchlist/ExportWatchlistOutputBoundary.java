
package use_case.export_watchlist;

/**
 * The output boundary for the Export Watchlist Use Case.
 */
public interface ExportWatchlistOutputBoundary {

    /**
     * Prepares the success view for the Export Watchlist Use Case.
     * @param outputData the output data for the Export Watchlist Use Case.
     */
    void prepareSuccessView(ExportWatchlistOutputData outputData);

    /**
     * Prepares the failure view for the Export Watchlist Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
