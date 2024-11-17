package use_case.rated_list;

public interface RatedListOutputBoundary {
    void prepareSuccessView(RatedListOutputData ratedListOutputData);
    void switchToLoggedInView(RatedListOutputData ratedListOutputData);
}
