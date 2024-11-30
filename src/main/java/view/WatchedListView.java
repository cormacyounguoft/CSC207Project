package view;

import interface_adapter.export_watchedlist.ExportWatchedListController;
import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list_remove.WatchedListRemoveController;
import interface_adapter.watched_list_remove.WatchedListRemoveState;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;

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

public class WatchedListView extends JPanel implements PropertyChangeListener {
    private static final int GRID_WATCHEDLIST_VGAP = 10;
    private static final int GRID_WATCHEDLIST_COLUMNS = 4;

    private final String viewName = "watched list";

    private final WatchedListRemoveViewModel watchedListRemoveViewModel;
    private WatchedListRemoveController watchedListRemoveController;
    private ExportWatchedListController exportWatchedListController;
    private GoRateController goToRateController;
    private ToLoggedInViewController goToLoggedInViewController;

    private final JButton cancel;
    private final JButton export;

    private final JLabel username;
    private final JPanel watchedList;
    private final JScrollPane scroller;

    public WatchedListView(WatchedListRemoveViewModel watchedListRemoveViewModel) {
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
        this.watchedListRemoveViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        final JLabel title = new JLabel("Watched List", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        username = new JLabel();
        username.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        username.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        watchedList = new JPanel();
        watchedList.setLayout(new GridLayout(0, GRID_WATCHEDLIST_COLUMNS, 0, GRID_WATCHEDLIST_VGAP)); // 4 movies per row
        watchedList.setBackground(new Color(Constants.COLOUR_B, Constants.COLOUR_B, Constants.COLOUR_B));

        scroller = new JScrollPane(watchedList);
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
            final WatchedListRemoveState currentState = watchedListRemoveViewModel.getState();
            goToLoggedInViewController.toLoggedInView(currentState.getUsername());
        });

        export.addActionListener(e -> {
            final WatchedListRemoveState currentState = watchedListRemoveViewModel.getState();
            exportWatchedListController.exportWatchedList(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchedListRemoveState state = (WatchedListRemoveState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
                state.setError(null);
            } else if (state.getExport() != null) {
                JOptionPane.showMessageDialog(this, state.getExport());
                state.setExport(null);
            } else {
                final List<String> moviePosters = state.getWatchedListUrl();
                final List<String> movieTitles = state.getWatchedListTitle();

                // Combine movie titles and posters into a single list for sorting
                List<Movie> movies = new ArrayList<>();
                for (int i = 0; i < movieTitles.size(); i++) {
                    movies.add(new Movie(movieTitles.get(i), moviePosters.get(i)));
                }

                // Sort movies by title
                movies.sort(Comparator.comparing(Movie::getTitle));

                watchedList.removeAll();

                for (Movie movie : movies) {
                    String poster = movie.getPosterUrl();
                    String movieTitle = movie.getTitle();

                    JPanel moviePanel = new JPanel(new BorderLayout());
                    JLabel titleLabel = new JLabel(movieTitle, SwingConstants.CENTER);
                    titleLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
                    moviePanel.add(titleLabel, BorderLayout.NORTH);

                    JLabel posterLabel = new JLabel();
                    posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    if (poster.isEmpty()) {
                        posterLabel.setText("Poster not available.");
                    } else {
                        try {
                            URL url = new URL(poster);
                            BufferedImage image = ImageIO.read(url);
                            Image scaledImage = image.getScaledInstance(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                            posterLabel.setIcon(new ImageIcon(scaledImage));
                        } catch (IOException e) {
                            posterLabel.setText("Poster not available.");
                        }
                    }

                    moviePanel.add(posterLabel, BorderLayout.CENTER);

                    // Add buttons for each movie
                    JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 0));
                    JButton removeButton = createStyledButton("Remove");
                    removeButton.addActionListener(evt1 -> {
                        watchedListRemoveController.execute(state.getUsername(), movieTitle);
                        JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has " +
                                "been removed from your watched list.");
                    });

                    JButton rateButton = createStyledButton("Rate");
                    rateButton.addActionListener(evt1 -> {
                        goToRateController.goToRate(state.getUsername(), movieTitle);
                    });

                    buttonPanel.add(removeButton);
                    buttonPanel.add(rateButton);
                    moviePanel.add(buttonPanel, BorderLayout.SOUTH);

                    watchedList.add(moviePanel);
                }

                username.setText("Currently logged in as: " + state.getUsername());
                watchedList.revalidate();
                watchedList.repaint();
            }
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B)); // Pastel blue
        button.setForeground(Color.BLACK); // Black text for visibility
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchedListController(WatchedListRemoveController watchedListRemoveController) {
        this.watchedListRemoveController = watchedListRemoveController;
    }

    public void setExportWatchedListController(ExportWatchedListController controller) {
        this.exportWatchedListController = controller;
    }

    public void setGoToRateController(GoRateController controller) {
        this.goToRateController = controller;
    }

    public void setGoToLoggedInViewController(ToLoggedInViewController controller) {
        this.goToLoggedInViewController = controller;
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
}
