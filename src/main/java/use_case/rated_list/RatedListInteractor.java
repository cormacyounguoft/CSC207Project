package use_case.rated_list;

public class RatedListInteractor implements RatedListInputBoundary{
    private final RatedListOutputBoundary presenter;
    private final RatedListDataAccessInterface useraccess;

    public RatedListInteractor(RatedListOutputBoundary presenter, RatedListDataAccessInterface useraccess) {
        this.presenter = presenter;
        this.useraccess = useraccess;
    }

    @Override
    public void execute(RatedListInputData ratedListInputData) {
        useraccess.removeUserRating(ratedListInputData.getUsername(), ratedListInputData.getTitle());
        presenter.prepareSuccessView();
    }

}
