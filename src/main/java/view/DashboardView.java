package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

/**
 * The view for the Dashboard Use Case.
 */
public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int GRID_DASHBOARD_GAP = 10;
    private static final int GRID_DASHBOARD_ROWS = 6;

    private final String viewName = "dashboard";

    private final DashboardViewModel dashboardViewModel;
    private DashboardController dashboardController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JLabel usernameLabel = new JLabel();
    private final JLabel totalHoursWatchedLabel = new JLabel();
    private final JLabel favoriteMovieLabel = new JLabel();
    private final JLabel favoriteGenreLabel = new JLabel();
    private final JLabel longestMovieLabel = new JLabel();
    private final JLabel averageRatingLabel = new JLabel();

    private final JButton cancel;

    public DashboardView(DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.dashboardViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        final JLabel title = new JLabel("Movie Dashboard", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(GRID_DASHBOARD_ROWS, 1, GRID_DASHBOARD_GAP, GRID_DASHBOARD_GAP));
        contentPanel.setBackground(new Color(Constants.COLOUR_B, Constants.COLOUR_B, Constants.COLOUR_B));
        contentPanel.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        configureLabel(usernameLabel, contentPanel);
        configureLabel(totalHoursWatchedLabel, contentPanel);
        configureLabel(favoriteMovieLabel, contentPanel);
        configureLabel(favoriteGenreLabel, contentPanel);
        configureLabel(longestMovieLabel, contentPanel);
        configureLabel(averageRatingLabel, contentPanel);

        this.add(contentPanel, BorderLayout.CENTER);

        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);

        cancel = createStyledButton("Cancel");
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        cancel.addActionListener(evt -> {
            final DashboardState currentState = dashboardViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    private void configureLabel(JLabel label, JPanel panel) {
        label.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_MEDIUM));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        panel.add(label);
    }

    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_MEDIUM));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G,
                Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R,
                Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No specific action handling for now
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final DashboardState state = (DashboardState) evt.getNewValue();
            usernameLabel.setText("Username: " + state.getUsername());
            totalHoursWatchedLabel.setText("Total Time Watched: " + state.getTotalHoursWatched());
            favoriteMovieLabel.setText("Favorite Movie: " + state.getFavoriteMovie());
            favoriteGenreLabel.setText("Favorite Genre: " + state.getFavoriteGenre());
            longestMovieLabel.setText("Longest Movie Watched: " + state.getLongestMovie());
            averageRatingLabel.setText("Average Rating: " + state.getAverageRating());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}

