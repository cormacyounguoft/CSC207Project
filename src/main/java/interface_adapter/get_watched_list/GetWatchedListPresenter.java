package interface_adapter.get_watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.watched_list.WatchedListState;
import interface_adapter.watched_list.WatchedListViewModel;
import use_case.get_watched_list.GetWatchedListOutputBoundary;
import use_case.get_watched_list.GetWatchedListOutputData;

public class GetWatchedListPresenter implements GetWatchedListOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private WatchedListViewModel watchedListViewModel;

    public GetWatchedListPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, WatchedListViewModel watchedListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.watchedListViewModel = watchedListViewModel;
    }

    @Override
    public void prepareSuccessView(GetWatchedListOutputData outputData) {
        final WatchedListState watchedListState = watchedListViewModel.getState();
        watchedListState.setUsername(outputData.getUsername());
        watchedListState.setWatchedList(outputData.getWatchedList());
        watchedListViewModel.setState(watchedListState);
        watchedListViewModel.firePropertyChanged();
        viewManagerModel.setState(watchedListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
