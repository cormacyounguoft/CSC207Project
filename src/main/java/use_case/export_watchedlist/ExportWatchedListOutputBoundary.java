package use_case.export_watchedlist;

public interface ExportWatchedListOutputBoundary {
    /**
     * Handles the success scenario of the export process.
     * @param outputData Output data containing export details.
     */
    void prepareSuccessView(ExportWatchedListOutputData outputData);

    /**
     * Handles the failure scenario of the export process.
     * @param errorMessage Error message detailing why the export failed.
     */
    void prepareFailView(String errorMessage);
}
