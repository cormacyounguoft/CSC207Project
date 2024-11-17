package use_case.rated_list;

public class RatedListInteractor implements RatedListInputBoundary{
    private final RatedListOutputBoundary presenter;

    public RatedListInteractor(RatedListOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(RatedListInputData ratedListInputData) {
        final RatedListOutputData ratedListOutputData = new RatedListOutputData(ratedListInputData.getUsername(), ratedListInputData.getRating(), false);
        presenter.prepareSuccessView(ratedListOutputData);
    }

    @Override
    public void switchToLoggedInView(RatedListInputData ratedListInputData) {
        final RatedListOutputData ratedListOutputData = new RatedListOutputData(ratedListInputData.getUsername(), ratedListInputData.getRating(), false);
        presenter.switchToLoggedInView(ratedListOutputData);
    }
}
