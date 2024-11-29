package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
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

        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        final JLabel title = new JLabel("Movie Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        this.add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 1, 10, 10));
        contentPanel.setBackground(new Color(255, 255, 255)); // White background for clarity
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

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
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(new Color(0, 51, 102));
        panel.add(label);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setBackground(new Color(93, 186, 255));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
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

