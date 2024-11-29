
package use_case.export_watchlist;

public interface ExportWatchlistOutputBoundary {
    void prepareSuccessView(ExportWatchlistOutputData outputData);

    void prepareFailView(String errorMessage);
}
