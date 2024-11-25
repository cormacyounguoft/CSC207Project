package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "dashboard";

    private final DashboardViewModel dashboardViewModel;
    private DashboardController dashboardController;

    private final JLabel usernameLabel;
    private final JLabel totalHoursWatchedLabel;
    private final JPanel favoriteGenresPanel;
    private final JPanel highestRatedGenresPanel;
    private final JPanel longestMoviesPanel;
    private final JLabel averageRatingLabel;

    private final JButton backButton;

    public DashboardView(DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.dashboardViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Movie Dashboard");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameLabel = new JLabel();
        totalHoursWatchedLabel = new JLabel();
        averageRatingLabel = new JLabel();

        favoriteGenresPanel = new JPanel();
        favoriteGenresPanel.setLayout(new BoxLayout(favoriteGenresPanel, BoxLayout.Y_AXIS));
        favoriteGenresPanel.setBorder(BorderFactory.createTitledBorder("Favorite Genres"));

        highestRatedGenresPanel = new JPanel();
        highestRatedGenresPanel.setLayout(new BoxLayout(highestRatedGenresPanel, BoxLayout.Y_AXIS));
        highestRatedGenresPanel.setBorder(BorderFactory.createTitledBorder("Highest-Rated Genres"));

        longestMoviesPanel = new JPanel();
        longestMoviesPanel.setLayout(new BoxLayout(longestMoviesPanel, BoxLayout.Y_AXIS));
        longestMoviesPanel.setBorder(BorderFactory.createTitledBorder("Longest Movies"));

        backButton = new JButton("Back to Logged-In View");

        backButton.addActionListener(
                evt -> {
                    DashboardState currentState = dashboardViewModel.getState();
                    dashboardController.switchToLoggedInView(currentState.getUsername());
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameLabel);
        this.add(totalHoursWatchedLabel);
        this.add(averageRatingLabel);
        this.add(favoriteGenresPanel);
        this.add(highestRatedGenresPanel);
        this.add(longestMoviesPanel);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No specific action handling for now
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            DashboardState state = (DashboardState) evt.getNewValue();
            usernameLabel.setText("Username: " + state.getUsername());
            totalHoursWatchedLabel.setText("Total Hours Watched: " + state.getTotalHoursWatched());
            averageRatingLabel.setText("Average Rating: " + state.getAverageRating());
            favoriteGenresPanel.removeAll();

            for (Map.Entry<String, Integer> entry : state.getFavoriteGenres().entrySet()) {
                JLabel genreLabel = new JLabel(entry.getKey() + ": " + entry.getValue() + " movies");
                favoriteGenresPanel.add(genreLabel);
            }

            highestRatedGenresPanel.removeAll();
            for (Map.Entry<String, Double> entry : state.getHighestRatedGenres().entrySet()) {
                JLabel genreLabel = new JLabel(entry.getKey() + ": " + String.format("%.2f", entry.getValue()) + " average rating");
                highestRatedGenresPanel.add(genreLabel);
            }

            longestMoviesPanel.removeAll();
            for (String movie : state.getLongestMovies()) {
                JLabel movieLabel = new JLabel(movie);
                longestMoviesPanel.add(movieLabel);
            }

            favoriteGenresPanel.revalidate();
            favoriteGenresPanel.repaint();
            highestRatedGenresPanel.revalidate();
            highestRatedGenresPanel.repaint();
            longestMoviesPanel.revalidate();
            longestMoviesPanel.repaint();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
