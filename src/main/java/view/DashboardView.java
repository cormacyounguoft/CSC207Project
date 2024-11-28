package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardState;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list.WatchedListState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "dashboard";

    private final DashboardViewModel dashboardViewModel;
    private DashboardController dashboardController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JLabel usernameLabel = new JLabel();
    private final JLabel totalHoursWatchedLabel = new JLabel();
    private final JLabel favoriteMovieLabel = new JLabel();
    private final JLabel favoriteGenreLable = new JLabel();
    private final JLabel longestMovieLabel = new JLabel();
    private final JLabel averageRatingLabel = new JLabel();

    private final JButton cancel;

    public DashboardView(DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.dashboardViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Movie Dashboard");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        cancel = new JButton(DashboardViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final DashboardState currentState = dashboardViewModel.getState();
                        toLoggedInViewController.toLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameLabel);
        this.add(totalHoursWatchedLabel);
        this.add(favoriteMovieLabel);
        this.add(favoriteGenreLable);
        this.add(longestMovieLabel);
        this.add(averageRatingLabel);
        this.add(buttons);
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
            favoriteGenreLable.setText("Favorite Genre: " + state.getFavoriteGenre());
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
