package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.get_rated_list.GetRatedListController;
import interface_adapter.get_watched_list.GetWatchedListController;
import interface_adapter.get_watchlist.GetWatchlistController;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements PropertyChangeListener {
    private static final int FONT_LOGGEDIN = 28;
    private static final int BUTTON_WIDTH = 400;
    private static final int BUTTON_HEIGHT = 100;
    private static final int GRID_LOGGEDIN_GAP = 20;
    private static final int BORDER_THICKNESS = 3;

    private final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;
    private LoggedInController loggedInController;
    private LogoutController logoutController;
    private GetWatchedListController watchedListController;
    private GetWatchlistController watchlistController;
    private GetRatedListController getRatedListController;
    private DashboardController dashboardController;

    private final JButton toSearch;
    private final JButton toWatchList;
    private final JButton toWatchedList;
    private final JButton toChangePassword;
    private final JButton logout;
    private final JButton toRatedList;
    private final JButton toDashboard; // New Dashboard Button

    private final JLabel username;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Light blue background
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT)); // Padding around the panel

        // Title Section
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        // View Title
        final JLabel title = new JLabel(loggedInViewModel.TITLE, SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Welcome Text
        username = new JLabel("Welcome!", SwingConstants.CENTER);
        username.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_MEDIUM));
        username.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT)); // Add spacing between title and welcome text
        titlePanel.add(username);
        this.add(titlePanel, BorderLayout.NORTH);

        // Buttons Section
        final JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, GRID_LOGGEDIN_GAP, GRID_LOGGEDIN_GAP)); // 3 rows, 2 columns
        buttonsPanel.setOpaque(false);

        toSearch = buttonFactory(loggedInViewModel.TO_SEARCH_BUTTON);
        toWatchList = buttonFactory(loggedInViewModel.TO_WATCHLIST_BUTTON);
        toWatchedList = buttonFactory(loggedInViewModel.TO_WATCHED_LIST_BUTTON);
        toChangePassword = buttonFactory(loggedInViewModel.TO_CHANGE_PASSWORD_BUTTON);
        logout = buttonFactory(loggedInViewModel.LOGOUT_BUTTON);
        toRatedList = buttonFactory("Go to Rated List");
        toDashboard = buttonFactory("Go to Dashboard");

        buttonsPanel.add(toSearch);
        buttonsPanel.add(toWatchList);
        buttonsPanel.add(toWatchedList);
        buttonsPanel.add(toRatedList);
        buttonsPanel.add(toDashboard);
        buttonsPanel.add(toChangePassword);
        buttonsPanel.add(logout);

        JPanel centeredPanel = new JPanel(new BorderLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.setBorder(new EmptyBorder(Constants.LOGGED_CENTERED_Y_AXIS, Constants.CENTERED_X_AXIS, Constants.LOGGED_CENTERED_Y_AXIS, Constants.CENTERED_X_AXIS)); // Padding around the buttons panel
        centeredPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(centeredPanel, BorderLayout.CENTER);

        // Action Listeners
        toSearch.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            loggedInController.switchToLoggedInSearchView(state.getUsername());
        });

        toWatchList.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            watchlistController.execute(state.getUsername());
        });

        toWatchedList.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            watchedListController.execute(state.getUsername());
        });

        toChangePassword.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            loggedInController.switchToChangePasswordView(state.getUsername());
        });

        logout.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            logoutController.execute(state.getUsername());
        });

        toRatedList.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            getRatedListController.execute(state.getUsername());
        });

        toDashboard.addActionListener(evt -> {
            LoggedInState state = loggedInViewModel.getState();
            dashboardController.execute(state.getUsername());
        });
    }

    private JButton buttonFactory(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, FONT_LOGGEDIN)); // Larger font size
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B)); // Pastel blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), BORDER_THICKNESS)); // Slightly thicker border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT)); // Larger button size
        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText("Welcome, " + state.getUsername() + "!");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInController(LoggedInController controller) {
        this.loggedInController = controller;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setWatchedListController(GetWatchedListController controller) {
        this.watchedListController = controller;
    }

    public void setWatchlistController(GetWatchlistController controller) {
        this.watchlistController = controller;
    }

    public void setGetRatedListController(GetRatedListController controller) {
        this.getRatedListController = controller;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
