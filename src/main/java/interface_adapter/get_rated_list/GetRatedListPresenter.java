package interface_adapter.get_rated_list;

import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.get_rated_list.GetRateListOutputBoundary;
import use_case.get_rated_list.GetRateListOutputData;


public class GetRatedListPresenter implements GetRateListOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private RatedListViewModel ratedListViewModel;

    public GetRatedListPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, RatedListViewModel ratedListViewModel ) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.ratedListViewModel = ratedListViewModel;
    }

    @Override
    public void prepareSuccessView(GetRateListOutputData outputData) {
        final RatedListState ratedListState = ratedListViewModel.getState();
        ratedListState.setUsername(outputData.getUsername());
        ratedListState.setRating(outputData.getUserRating());
        ratedListViewModel.setState(ratedListState);
        ratedListViewModel.firePropertyChanged();
        viewManagerModel.setState(ratedListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
