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
