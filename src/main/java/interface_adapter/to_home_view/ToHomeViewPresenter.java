package interface_adapter.to_home_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.to_home_view.ToHomeViewOutputBoundary;

public class ToHomeViewPresenter implements ToHomeViewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final SearchViewModel searchViewModel;

    public ToHomeViewPresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final SignupState signupState = signupViewModel.getState();
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        signupState.setUsername("");
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        final SearchState searchState = searchViewModel.getState();
        searchState.setSearchQuery("");
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
