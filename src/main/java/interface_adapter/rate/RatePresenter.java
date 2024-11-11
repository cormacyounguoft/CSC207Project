package interface_adapter.rate;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.rate.RateOutputBoundary;
import use_case.rate.RateOutputData;

public class RatePresenter implements RateOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RateViewModel rateViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public RatePresenter(ViewManagerModel viewManagerModel,
                         RateViewModel rateViewModel,
                         LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rateViewModel = rateViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(RateOutputData outputData) {
        LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        RateState state = rateViewModel.getState();
        state.setRateError(errorMessage);
        rateViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView(RateOutputData outputData) {
        LoggedInState state = loggedInViewModel.getState();
        state.setUsername(outputData.getUsername());
        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
