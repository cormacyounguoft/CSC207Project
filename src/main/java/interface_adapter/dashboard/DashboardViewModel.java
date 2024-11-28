package interface_adapter.dashboard;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Dashboard View.
 */
public class DashboardViewModel extends ViewModel<DashboardState> {
    public static final String TITLE_LABEL = "Dashboard View";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public DashboardViewModel() {
        super("dashboard");
        setState(new DashboardState());
    }
}
