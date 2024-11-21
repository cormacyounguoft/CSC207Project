package interface_adapter.to_home_view;

import use_case.to_home_view.ToHomeViewInputBoundary;

public class ToHomeViewController {
    private final ToHomeViewInputBoundary interactor;

    public ToHomeViewController(ToHomeViewInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void toHomeView(){
        interactor.toHomeView();
    }
}
