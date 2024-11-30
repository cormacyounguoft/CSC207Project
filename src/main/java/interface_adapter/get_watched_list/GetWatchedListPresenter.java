package interface_adapter.get_watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.watched_list_remove.WatchedListState;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;
import use_case.get_watched_list.GetWatchedListOutputBoundary;
import use_case.get_watched_list.GetWatchedListOutputData;

/**
 * The presenter for the get watched list use case.
 */
public class GetWatchedListPresenter implements GetWatchedListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchedListRemoveViewModel watchedListRemoveViewModel;

    public GetWatchedListPresenter(ViewManagerModel viewManagerModel,
                                   WatchedListRemoveViewModel watchedListRemoveViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
    }

    @Override
    public void prepareSuccessView(GetWatchedListOutputData outputData) {
        final WatchedListState watchedListState = watchedListRemoveViewModel.getState();
        watchedListState.setUsername(outputData.getUsername());
        watchedListState.setWatchedListTitle(outputData.getWatchedListTitle());
        watchedListState.setWatchedListUrl(outputData.getWatchedListUrl());
        watchedListRemoveViewModel.setState(watchedListState);
        watchedListRemoveViewModel.firePropertyChanged();
        viewManagerModel.setState(watchedListRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
