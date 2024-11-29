package use_case.rated_list;

/**
 * The interactor for the rated list use case.
 */
public class RatedListInteractor implements RatedListInputBoundary {

    private final RatedListOutputBoundary presenter;
    private final RatedListDataAccessInterface useraccess;

    public RatedListInteractor(RatedListOutputBoundary presenter, RatedListDataAccessInterface useraccess) {
        this.presenter = presenter;
        this.useraccess = useraccess;
    }

    @Override
    public void execute(RatedListInputData ratedListInputData) {
        useraccess.removeUserRating(ratedListInputData.getUsername(), ratedListInputData.getTitle());
        final RatedListOutputData outputData = new RatedListOutputData(ratedListInputData.getUsername(), false);
        presenter.prepareSuccessView(outputData);
    }
}
