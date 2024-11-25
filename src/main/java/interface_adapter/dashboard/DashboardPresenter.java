package interface_adapter.dashboard;

import java.util.ArrayList;
import java.util.HashMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;
import use_case.logged_in.LoggedInOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final DashboardViewModel dashboardViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              LoggedInViewModel loggedInViewModel,
                              DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void prepareSuccessView(DashboardOutputData outputData) {
        DashboardState currentState = dashboardViewModel.getState();

        currentState.setTotalHoursWatched(outputData.getTotalHoursWatched());
        currentState.setFavoriteGenres(outputData.getFavoriteGenres() != null ? outputData.getFavoriteGenres() : new HashMap<>());
        currentState.setAverageRating(outputData.getAverageRating());
        currentState.setHighestRatedGenres(outputData.getHighestRatedGenres() != null ? outputData.getHighestRatedGenres() : new HashMap<>());
        currentState.setLongestMovies(outputData.getLongestMovies() != null ? outputData.getLongestMovies() : new ArrayList<>());

        dashboardViewModel.setState(currentState);
        dashboardViewModel.firePropertyChanged();
    }


    @Override
    public void switchToLoggedInView(DashboardOutputData outputData) {
        final LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void switchToDashboardView(DashboardOutputData outputData) {
        final DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setUsername(outputData.getUsername());
        dashboardState.setTotalHoursWatched(outputData.getTotalHoursWatched());
        dashboardState.setFavoriteGenres(outputData.getFavoriteGenres() != null ? outputData.getFavoriteGenres() : new HashMap<>());
        dashboardState.setAverageRating(outputData.getAverageRating());
        dashboardState.setHighestRatedGenres(outputData.getHighestRatedGenres() != null ? outputData.getHighestRatedGenres() : new HashMap<>());
        dashboardState.setLongestMovies(outputData.getLongestMovies() != null ? outputData.getLongestMovies() : new ArrayList<>());
        dashboardViewModel.setState(dashboardState);
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setState(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}