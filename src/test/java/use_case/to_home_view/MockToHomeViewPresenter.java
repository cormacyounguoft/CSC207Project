package use_case.to_home_view;

public class MockToHomeViewPresenter implements ToHomeViewOutputBoundary {
    public boolean wasSwitchToLoginViewCalled = false;

    @Override
    public void prepareSuccessView() {
        wasSwitchToLoginViewCalled = true;
    }
}
