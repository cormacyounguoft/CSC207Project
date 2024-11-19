package use_case.rate;

public interface RateOutputBoundary {

    void prepareSuccessView(RateOutputData outputData);

    void prepareFailView(String errorMessage);
}
