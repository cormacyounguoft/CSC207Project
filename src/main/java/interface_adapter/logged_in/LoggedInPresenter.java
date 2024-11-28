package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.logged_in_search.LoggedInSearchState;
import interface_adapter.logged_in_search.LoggedInSearchViewModel;
import interface_adapter.watched_list_remove.WatchedListRemoveState;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;
import interface_adapter.watchlist_remove.WatchlistRemoveState;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.logged_in.LoggedInOutputData;

/**
 * The Presenter for the Logged In Use Case.
 */
public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInSearchViewModel loggedInSearchViewModel;
    private final WatchlistRemoveViewModel watchlistViewModel;
    private final WatchedListRemoveViewModel watchedListRemoveViewModel;
    private final DashboardViewModel dashboardViewModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             ChangePasswordViewModel changePasswordViewModel,
                             LoggedInSearchViewModel loggedInSearchViewModel,
                             WatchlistRemoveViewModel watchlistViewModel,
                             WatchedListRemoveViewModel watchedListRemoveViewModel,
                             DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loggedInSearchViewModel = loggedInSearchViewModel;
        this.watchlistViewModel = watchlistViewModel;
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void switchToChangePasswordView(LoggedInOutputData loggedInOutputData) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setUsername(loggedInOutputData.getUsername());
        changePasswordState.setPasswordError(null);
        changePasswordViewModel.setState(changePasswordState);
        changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInSearchView(LoggedInOutputData loggedInOutputData) {
        final LoggedInSearchState loggedInSearchState = loggedInSearchViewModel.getState();
        loggedInSearchState.setUsername(loggedInOutputData.getUsername());
        loggedInSearchViewModel.setState(loggedInSearchState);
        loggedInSearchViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWatchListView(LoggedInOutputData loggedInOutputData) {
        final WatchlistRemoveState watchlistState = watchlistViewModel.getState();
        watchlistState.setUsername(loggedInOutputData.getUsername());
        watchlistViewModel.setState(watchlistState);
        watchlistViewModel.firePropertyChanged();

        viewManagerModel.setState(watchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWatchedListView(LoggedInOutputData loggedInOutputData) {
        final WatchedListRemoveState watchedListRemoveState = watchedListRemoveViewModel.getState();
        watchedListRemoveState.setUsername(loggedInOutputData.getUsername());
        watchedListRemoveViewModel.setState(watchedListRemoveState);
        watchedListRemoveViewModel.firePropertyChanged();

        viewManagerModel.setState(watchedListRemoveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDashboardView(LoggedInOutputData loggedInOutputData) {
        final DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setUsername(loggedInOutputData.getUsername());
        dashboardViewModel.setState(dashboardState);
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setState(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
