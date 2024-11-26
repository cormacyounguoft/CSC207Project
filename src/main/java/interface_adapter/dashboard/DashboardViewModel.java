package interface_adapter.dashboard;
import interface_adapter.ViewModel;

/**
 * The ViewModel for the Dashboard View.
 */
public class DashboardViewModel extends ViewModel<DashboardState> {
    public static final String TITLE_LABEL = "Dashboard Overview";
    public DashboardViewModel() {
        super("dashboard");
        setState(new DashboardState());
    }
}
