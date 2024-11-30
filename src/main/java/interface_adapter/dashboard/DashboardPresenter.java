package interface_adapter.dashboard;

import interface_adapter.ViewManagerModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;

/**
 * The presenter for the dashboard of the application.
 */
public class DashboardPresenter implements DashboardOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    public DashboardPresenter(ViewManagerModel viewManagerModel,
                              DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void prepareSuccessView(DashboardOutputData outputData) {
        dashboardViewModel.getState().setTotalHoursWatched(outputData.getTotalHoursWatched());
        dashboardViewModel.getState().setFavoriteMovie(outputData.getFavoriteMovie());
        dashboardViewModel.getState().setFavoriteGenre(outputData.getFavoriteGenre());
        dashboardViewModel.getState().setAverageRating(outputData.getAverageRating());
        dashboardViewModel.getState().setLongestMovie(outputData.getLongestMovie());
        dashboardViewModel.getState().setUsername(outputData.getUsername());

        dashboardViewModel.setState(dashboardViewModel.getState());
        dashboardViewModel.firePropertyChanged();

        viewManagerModel.setState(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
