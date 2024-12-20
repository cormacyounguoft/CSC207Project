package interface_adapter.get_rated_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import use_case.get_rated_list.GetRateListOutputBoundary;
import use_case.get_rated_list.GetRateListOutputData;

/**
 * The presenter for the get rated list use case.
 */
public class GetRatedListPresenter implements GetRateListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final RatedListViewModel ratedListViewModel;

    public GetRatedListPresenter(ViewManagerModel viewManagerModel, RatedListViewModel ratedListViewModel) {
        this.viewManagerModel = viewManagerModel;
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
