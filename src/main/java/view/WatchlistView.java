package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.export_watchlist.ExportWatchlistController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watchlist_remove.WatchlistRemoveController;
import interface_adapter.watchlist_remove.WatchlistRemoveViewModel;
import interface_adapter.watchlist_remove.WatchlistState;

/**
 * The View for the Watch List Use Case.
 */
public class WatchlistView extends JPanel implements PropertyChangeListener {
    private final String viewName = "watchlist";

    private final WatchlistRemoveViewModel watchlistRemoveViewModel;
    private WatchlistRemoveController watchlistRemoveController;
    private ToLoggedInViewController toLoggedInViewController;
    private AddToWatchedListController addToWatchedListController;
    private ExportWatchlistController exportWatchlistController;

    private final JButton cancel;
    private final JButton export;

    private final JLabel username;
    private final JPanel watchlist;
    private final JScrollPane scroller;

    public WatchlistView(WatchlistRemoveViewModel watchlistRemoveViewModel) {
        this.watchlistRemoveViewModel = watchlistRemoveViewModel;
        this.watchlistRemoveViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        final JLabel title = new JLabel("Watchlist", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        username = new JLabel();
        username.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        username.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        watchlist = new JPanel();
        watchlist.setLayout(new GridLayout(Constants.GRID_ROW, Constants.GRID_COLUMN, Constants.GRID_H_GAP,
                Constants.GRID_V_GAP));
        watchlist.setBackground(new Color(Constants.BACKGROUND_COLOUR_B, Constants.BACKGROUND_COLOUR_B,
                Constants.BACKGROUND_COLOUR_B));

        scroller = new JScrollPane(watchlist);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(Constants.SCROLLER_WIDTH, Constants.SCROLLER_HEIGHT));
        this.add(scroller, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);

        cancel = createStyledButton("Cancel");
        buttonsPanel.add(cancel);

        export = createStyledButton("Export");
        buttonsPanel.add(export);

        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        cancel.addActionListener(evt -> {
            final WatchlistState currentState = watchlistRemoveViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });

        export.addActionListener(evt -> {
            final WatchlistState currentState = watchlistRemoveViewModel.getState();
            exportWatchlistController.exportWatchlist(currentState.getUsername());
        });

        buttonsPanel.add(export);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchlistState state = (WatchlistState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
                state.setError(null);
            }
            else if (state.getExport() != null) {
                JOptionPane.showMessageDialog(this, state.getExport());
                state.setExport(null);
            }
            else {
                final List<String> moviePosters = state.getWatchlistUrl();
                final List<String> movieTitles = state.getWatchlistTitle();

                // Combine movie titles and posters into a single list for sorting
                final List<Movie> movies = new ArrayList<>();
                for (int i = 0; i < movieTitles.size(); i++) {
                    movies.add(new Movie(movieTitles.get(i), moviePosters.get(i)));
                }

                // Sort movies by title
                movies.sort(Comparator.comparing(Movie::getTitle));

                watchlist.removeAll();

                for (Movie movie : movies) {
                    final String poster = movie.getPosterUrl();
                    final String movieTitle = movie.getTitle();

                    final JPanel moviePanel = new JPanel(new BorderLayout());
                    final JLabel titleLabel = new JLabel(movieTitle, SwingConstants.CENTER);
                    titleLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
                    moviePanel.add(titleLabel, BorderLayout.NORTH);

                    final JLabel posterLabel = new JLabel();
                    posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    if (poster.isEmpty()) {
                        posterLabel.setText("Poster not available.");
                    }
                    else {
                        try {
                            final URL url = new URL(poster);
                            final BufferedImage image = ImageIO.read(url);
                            final Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                            posterLabel.setIcon(new ImageIcon(scaledImage));
                        }
                        catch (IOException exception) {
                            posterLabel.setText(Constants.NO_POSTER);
                        }
                    }

                    moviePanel.add(posterLabel, BorderLayout.CENTER);

                    // Add buttons for each movie
                    final JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 0));
                    final JButton removeButton = createStyledButton("Remove");
                    removeButton.addActionListener(evt1 -> {
                        final WatchlistState currentState = watchlistRemoveViewModel.getState();
                        watchlistRemoveController.execute(currentState.getUsername(), movieTitle);
                        toLoggedInViewController.toLoggedInView(currentState.getUsername());
                        JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has "
                                + "been removed from your watchlist.");
                    });

                    final JButton moveToWatchedButton = createStyledButton("Watched");
                    moveToWatchedButton.addActionListener(evt1 -> {
                        final WatchlistState currentState = watchlistRemoveViewModel.getState();
                        addToWatchedListController.execute(currentState.getUsername(), movieTitle);
                        watchlistRemoveController.execute(currentState.getUsername(), movieTitle);
                        JOptionPane.showMessageDialog(null, "\"" + movieTitle
                                + "\" has " + "been added to your watched list and removed from your watchlist.");
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
    }

    /**
     * Creates a styled button.
     * @param text the text to be displayed in the button.
     * @return the styled button that is created.
     */
    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_LARGER));
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

    public String getViewName() {
        return viewName;
    }

    public void setWatchlistRemoveController(WatchlistRemoveController watchlistRemoveController) {
        this.watchlistRemoveController = watchlistRemoveController;
    }

    public void setAddToWatchedListController(AddToWatchedListController controller) {
        this.addToWatchedListController = controller;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }

    public void setExportWatchlistController(ExportWatchlistController exportWatchlistController) {
        this.exportWatchlistController = exportWatchlistController;
    }

    /**
     * Helper class for sorting movies.
     */
    private static class Movie {
        private final String title;
        private final String posterUrl;

        Movie(String title, String posterUrl) {
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

}
