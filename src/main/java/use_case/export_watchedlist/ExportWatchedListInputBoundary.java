package use_case.export_watchedlist;

/**
 * The input boundary for the Export Watched List Use Case.
 */
public interface ExportWatchedListInputBoundary {

    /**
     * Exports the watched list of the user into a txt file.
     * @param inputData the input data for the export watched list use case.
     */
    void exportWatchedList(ExportWatchedListInputData inputData);
}
