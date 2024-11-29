package interface_adapter.get_watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.watched_list_remove.WatchedListRemoveState;
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
        final WatchedListRemoveState watchedListRemoveState = watchedListRemoveViewModel.getState();
        watchedListRemoveState.setUsername(outputData.getUsername());
        watchedListRemoveState.setWatchedListTitle(outputData.getWatchedListTitle());
        watchedListRemoveState.setWatchedListUrl(outputData.getWatchedListUrl());
        watchedListRemoveViewModel.setState(watchedListRemoveState);
        watchedListRemoveViewModel.firePropertyChanged();
        viewManagerModel.setState(watchedListRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
