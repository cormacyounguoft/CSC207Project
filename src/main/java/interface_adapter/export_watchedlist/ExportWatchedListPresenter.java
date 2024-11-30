package interface_adapter.export_watchedlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watched_list_remove.WatchedListRemoveState;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;
import use_case.export_watchedlist.ExportWatchedListOutputBoundary;
import use_case.export_watchedlist.ExportWatchedListOutputData;

/**
 * The presenter for the export watched list use case.
 */
public class ExportWatchedListPresenter implements ExportWatchedListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchedListRemoveViewModel watchedListRemoveViewModel;

    public ExportWatchedListPresenter(ViewManagerModel viewManagerModel,
                                      WatchedListRemoveViewModel watchedListRemoveViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
    }

    @Override
    public void prepareSuccessView(ExportWatchedListOutputData outputData) {
        final WatchedListRemoveState watchedListRemoveState = watchedListRemoveViewModel.getState();
        watchedListRemoveState.setExport("Watched list successfully exported to: " + outputData.getFilePath());
        watchedListRemoveViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchedListRemoveState watchedListRemoveState = watchedListRemoveViewModel.getState();
        watchedListRemoveState.setError(errorMessage);
        watchedListRemoveViewModel.firePropertyChanged();
    }
}
