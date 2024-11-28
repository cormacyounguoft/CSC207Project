package view;

import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watchlist_remove.WatchlistRemoveController;
import interface_adapter.watchlist_remove.WatchlistRemoveState;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WatchlistView extends JPanel implements PropertyChangeListener {
    private final String viewName = "watchlist";

    private final WatchlistRemoveViewModel watchlistRemoveViewModel;
    private WatchlistRemoveController watchlistRemoveController;
    private ToLoggedInViewController toLoggedInViewController;
    private AddToWatchedListController addToWatchedListController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel watchlist;
    private final JScrollPane scroller;

    public WatchlistView(WatchlistRemoveViewModel watchlistRemoveViewModel) {
        this.watchlistRemoveViewModel = watchlistRemoveViewModel;
        this.watchlistRemoveViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel("Watchlist", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        this.add(title, BorderLayout.NORTH);

        // Username Section
        username = new JLabel();
        username.setFont(new Font("SansSerif", Font.PLAIN, 16));
        username.setForeground(new Color(0, 51, 102));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        // Watchlist Content Panel
        watchlist = new JPanel();
        watchlist.setLayout(new GridLayout(0, 4, 30, 10)); // 4 movies per row, horizontal spacing > vertical spacing
        watchlist.setBackground(new Color(255, 255, 255)); // White background for clarity

        scroller = new JScrollPane(watchlist);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(900, 500));
        this.add(scroller, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);

        cancel = createStyledButton("Cancel");
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        cancel.addActionListener(evt -> {
            final WatchlistRemoveState currentState = watchlistRemoveViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchlistRemoveState state = (WatchlistRemoveState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchlistURL();
            final List<String> movieTitles = state.getWatchlistTitle();

            // Combine movie titles and posters into a single list for sorting
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < movieTitles.size(); i++) {
                movies.add(new Movie(movieTitles.get(i), moviePosters.get(i)));
            }

            // Sort movies by title
            movies.sort(Comparator.comparing(Movie::getTitle));

            watchlist.removeAll();

            for (Movie movie : movies) {
                String poster = movie.getPosterUrl();
                String movieTitle = movie.getTitle();

                JPanel moviePanel = new JPanel(new BorderLayout());
                JLabel titleLabel = new JLabel(movieTitle, SwingConstants.CENTER);
                titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
                moviePanel.add(titleLabel, BorderLayout.NORTH);

                JLabel posterLabel = new JLabel();
                posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                if (poster.isEmpty()) {
                    posterLabel.setText("Poster not available.");
                } else {
                    try {
                        URL url = new URL(poster);
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                        posterLabel.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException e) {
                        posterLabel.setText("Poster not available.");
                    }
                }

                moviePanel.add(posterLabel, BorderLayout.CENTER);

                // Add buttons for each movie
                JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
                JButton removeButton = createStyledButton("Remove");
                removeButton.addActionListener(evt1 -> {
                    final WatchlistRemoveState currentState = watchlistRemoveViewModel.getState();
                    watchlistRemoveController.execute(currentState.getUsername(), movieTitle);
                    toLoggedInViewController.toLoggedInView(currentState.getUsername());
                    JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has " +
                            "been removed from your watchlist.");
                });

                JButton moveToWatchedButton = createStyledButton("Watched");
                moveToWatchedButton.addActionListener(evt1 -> {
                    final WatchlistRemoveState currentState = watchlistRemoveViewModel.getState();
                    addToWatchedListController.execute(currentState.getUsername(), movieTitle);
                    watchlistRemoveController.execute(currentState.getUsername(), movieTitle);
                    JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has " +
                            "been added to your watched list and removed from your watchlist.");
                });

                buttonPanel.add(removeButton);
                buttonPanel.add(moveToWatchedButton);
                moviePanel.add(buttonPanel, BorderLayout.SOUTH);

                watchlist.add(moviePanel);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            watchlist.revalidate();
            watchlist.repaint();
        }
    }

    // Helper class for sorting movies
    private static class Movie {
        private final String title;
        private final String posterUrl;

        public Movie(String title, String posterUrl) {
            this.title = title;
            this.posterUrl = posterUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getPosterUrl() {
            return posterUrl;
        }
    }


    /**
     * Creates a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 22));
        button.setBackground(new Color(93, 186, 255)); // Pastel blue
        button.setForeground(Color.BLACK); // Black text for visibility
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchlistController(WatchlistRemoveController watchlistController) {
        this.watchlistRemoveController = watchlistController;
    }

    public void setAddToWatchedListController(AddToWatchedListController controller) {
        this.addToWatchedListController = controller;
    }
    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
    public void setWatchlistremovecontroller(WatchlistRemoveController watchlistremovecontroller) {
        this.watchlistRemoveController = watchlistremovecontroller;
    }

}
