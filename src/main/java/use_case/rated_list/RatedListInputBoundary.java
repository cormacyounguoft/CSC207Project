package use_case.rated_list;

public interface RatedListInputBoundary {
    void execute(RatedListInputData ratedListInputData);

    void switchToLoggedInView(RatedListInputData ratedListInputData);
}
