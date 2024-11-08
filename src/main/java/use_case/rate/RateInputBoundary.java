package use_case.rate;

public interface RateInputBoundary {

    void execute(RateInputData inputData);

    void switchToLoggedInView(RateInputData inputData);
}
