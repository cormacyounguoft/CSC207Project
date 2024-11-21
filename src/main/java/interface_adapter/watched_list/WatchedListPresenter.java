package interface_adapter.watched_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.watched_list.WatchedListOutputBoundary;
import use_case.watched_list.WatchedListOutputData;

public class WatchedListPresenter implements WatchedListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WatchedListViewModel watchedListViewModel;

    public WatchedListPresenter(ViewManagerModel viewManagerModel,
                                WatchedListViewModel watchedListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchedListViewModel = watchedListViewModel;
    }


    @Override
    public void prepareSuccessView(WatchedListOutputData outputData) {
        watchedListViewModel.firePropertyChanged();
    }
}
