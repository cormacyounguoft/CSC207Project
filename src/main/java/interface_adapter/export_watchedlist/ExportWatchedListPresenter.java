package interface_adapter.export_watchedlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;
import interface_adapter.watched_list_remove.WatchedListState;
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
        final WatchedListState watchedListState = watchedListRemoveViewModel.getState();
        watchedListState.setExport("Watched list successfully exported");
        watchedListRemoveViewModel.firePropertyChanged();

        viewManagerModel.setState(watchedListRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchedListState watchedListState = watchedListRemoveViewModel.getState();
        watchedListState.setError(errorMessage);
        watchedListRemoveViewModel.firePropertyChanged();
    }
}
