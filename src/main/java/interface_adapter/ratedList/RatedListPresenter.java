package interface_adapter.ratedList;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.rated_list.RatedListOutputBoundary;
import use_case.rated_list.RatedListOutputData;

public class RatedListPresenter implements RatedListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RatedListViewModel ratedListViewModel;

    public RatedListPresenter(ViewManagerModel viewManagerModel,
                              RatedListViewModel ratedListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.ratedListViewModel = ratedListViewModel;
    }


    @Override
    public void prepareSuccessView(RatedListOutputData outputData) {
        ratedListViewModel.firePropertyChanged();
    }
}
