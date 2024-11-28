package interface_adapter.to_home_view;

import use_case.to_home_view.ToHomeViewInputBoundary;

/**
 * The controller for the ToHomeView Use Case.
 */
public class ToHomeViewController {
    private final ToHomeViewInputBoundary interactor;

    public ToHomeViewController(ToHomeViewInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Takes user to the home view.
     */
    public void toHomeView() {
        interactor.toHomeView();
    }
}
