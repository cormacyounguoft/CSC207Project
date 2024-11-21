package interface_adapter.to_home_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.to_home_view.ToHomeViewOutputBoundary;

public class ToHomeViewPresenter implements ToHomeViewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public ToHomeViewPresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
