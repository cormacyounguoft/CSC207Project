package use_case.to_logged_in_view;

/**
 * Interactor for to logged in view.
 */
public class ToLoggedInViewInteractor implements ToLoggedInViewInputBoundary {
    private final ToLoggedInViewOutputBoundary presenter;

    public ToLoggedInViewInteractor(ToLoggedInViewOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void toLoggedInView(ToLoggedInViewInputData inputData) {
        final ToLoggedInViewOutputData outputData = new ToLoggedInViewOutputData(inputData.getUsername());
        presenter.prepareSuccessView(outputData);
    }
}
