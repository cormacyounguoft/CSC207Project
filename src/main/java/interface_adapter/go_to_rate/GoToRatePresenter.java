package interface_adapter.go_to_rate;

import interface_adapter.ViewManagerModel;
import interface_adapter.rate.RateState;
import interface_adapter.rate.RateViewModel;
import use_case.go_to_rate.GoToRateOutputBoundary;
import use_case.go_to_rate.GoToRateOutputData;

public class GoToRatePresenter implements GoToRateOutputBoundary {
    private final ViewManagerModel viewManager;
    private final RateViewModel rateViewModel;
    public GoToRatePresenter(ViewManagerModel viewManager, RateViewModel rateViewModel) {
        this.viewManager = viewManager;
        this.rateViewModel = rateViewModel;
    }

    @Override
    public void switchToRateView(GoToRateOutputData outputData) {
        final RateState state = rateViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setMovie(outputData.getMovie());
        rateViewModel.setState(state);
        rateViewModel.firePropertyChanged();

        viewManager.setState(rateViewModel.getViewName());
        viewManager.firePropertyChanged();
    }
}
