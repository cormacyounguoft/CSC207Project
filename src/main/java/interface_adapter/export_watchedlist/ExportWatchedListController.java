package interface_adapter.export_watchedlist;

import use_case.export_watchedlist.ExportWatchedListInputBoundary;
import use_case.export_watchedlist.ExportWatchedListInputData;

/**
 * Controller for exporting the watched list.
 */
public class ExportWatchedListController {
    private final ExportWatchedListInputBoundary interactor;

    public ExportWatchedListController(ExportWatchedListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Exports the watched list for the given user ID.
     * @param userId The user ID whose watched list is to be exported.
     */
    public void exportWatchedList(String userId) {
        ExportWatchedListInputData inputData = new ExportWatchedListInputData(userId);
        interactor.exportWatchedList(inputData);
    }
}
