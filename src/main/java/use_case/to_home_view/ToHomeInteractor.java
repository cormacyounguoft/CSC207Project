package use_case.to_home_view;

/**
 * Interactor for to home view.
 */
public class ToHomeInteractor implements ToHomeViewInputBoundary {

    private final ToHomeViewOutputBoundary presenter;

    public ToHomeInteractor(ToHomeViewOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void toHomeView() {
        presenter.prepareSuccessView();
    }
}
