package interface_adapter.go_to_rate;

import interface_adapter.ViewManagerModel;
import interface_adapter.rate.RateState;
import interface_adapter.rate.RateViewModel;
import use_case.get_watched_list.GetWatchedListOutputBoundary;
import use_case.go_to_rate.GoRateOutputBoundary;
import use_case.go_to_rate.GoRateOutputData;
import use_case.logged_in_search_result.LoggedInSearchResultOutputData;
import view.ViewManager;

import javax.swing.text.View;

public class GoRatePresenter implements GoRateOutputBoundary {
    private final ViewManagerModel viewManager;
    private final RateViewModel rateViewModel;
    public GoRatePresenter(ViewManagerModel viewManager, RateViewModel rateViewModel) {
        this.viewManager = viewManager;
        this.rateViewModel = rateViewModel;
    }

    @Override
    public void switchToRateView(GoRateOutputData outputData) {
        final RateState state = rateViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setTitle(outputData.getTitle());
        rateViewModel.setState(state);
        rateViewModel.firePropertyChanged();

        viewManager.setState(rateViewModel.getViewName());
        viewManager.firePropertyChanged();
    }
}
