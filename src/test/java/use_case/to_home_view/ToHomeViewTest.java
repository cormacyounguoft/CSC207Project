package use_case.to_home_view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToHomeViewTest {

    @Test
    public void testToHomeView() {
        MockToHomeViewPresenter presenter = new MockToHomeViewPresenter();
        ToHomeViewInputBoundary interactor = new ToHomeInteractor(presenter);

        interactor.toHomeView();
        assertTrue(presenter.wasSwitchToLoginViewCalled);

    }
}
