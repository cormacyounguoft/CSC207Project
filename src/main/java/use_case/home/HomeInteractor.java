package use_case.home;

/**
 * The Home Interactor.
 */
public class HomeInteractor implements HomeInputBoundary {
    private final HomeOutputBoundary presenter;

    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.presenter = homeOutputBoundary;
    }

    @Override
    public void switchToLoginView() {
        presenter.switchToLoginView();
    }

    @Override
    public void switchToSignupView() {
        presenter.switchToSignupView();
    }

    @Override
    public void switchToSearchView() {
        presenter.switchToSearchView();
    }
}
