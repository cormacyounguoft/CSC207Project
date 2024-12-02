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

import interface_adapter.export_watchedlist.ExportWatchedListController;
import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list_remove.WatchedListRemoveController;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;
import interface_adapter.watched_list_remove.WatchedListState;

/**
 * View for the Watched List.
 */
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

    private JLabel username;
    private JPanel watchedList;
    private JScrollPane scroller;

    public WatchedListView(WatchedListRemoveViewModel watchedListRemoveViewModel) {
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
        this.watchedListRemoveViewModel.addPropertyChangeListener(this);

        configureLayout();
        this.cancel = createStyledButton("Cancel");
        this.export = createStyledButton("Export");
        setupButtons();
    }

    private void configureLayout() {
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        setupTitleLabel();
        setupUsernameLabel();
        setupWatchedListPanel();
    }

    private void setupTitleLabel() {
        final JLabel title = new JLabel("Watched List", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);
    }

    private void setupUsernameLabel() {
        this.username = new JLabel();
        this.username.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        this.username.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G,
                Constants.FONT_COLOUR_B));
        this.username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);
    }

    private void setupWatchedListPanel() {
        this.watchedList = new JPanel();
        this.watchedList.setLayout(new GridLayout(0, GRID_WATCHEDLIST_COLUMNS, 0, GRID_WATCHEDLIST_VGAP));
        this.watchedList.setBackground(new Color(Constants.COLOUR_B, Constants.COLOUR_B, Constants.COLOUR_B));

        this.scroller = new JScrollPane(watchedList);
        this.scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scroller.setPreferredSize(new Dimension(Constants.SCROLLER_WIDTH, Constants.SCROLLER_HEIGHT));
        this.add(scroller, BorderLayout.CENTER);
    }

    private void setupButtons() {
        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);

        cancel.addActionListener(evt -> {
            goToLoggedInViewController.toLoggedInView(
                    watchedListRemoveViewModel.getState().getUsername());
        });

        export.addActionListener(evt -> {
            exportWatchedListController.exportWatchedList(
                    watchedListRemoveViewModel.getState().getUsername());
        });

        buttonsPanel.add(cancel);
        buttonsPanel.add(export);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            handleStateChange((WatchedListState) evt.getNewValue());
        }
    }

    private void handleStateChange(WatchedListState state) {
        if (state.getError() != null) {
            displayMessage(state.getError());
            state.setError(null);
        }
        else if (state.getExport() != null) {
            displayMessage(state.getExport());
            state.setExport(null);
        }
        else {
            updateWatchedList(state);
        }
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void updateWatchedList(WatchedListState state) {
        final List<Movie> movies = prepareMovieList(state);
        watchedList.removeAll();

        for (Movie movie : movies) {
            watchedList.add(createMoviePanel(state, movie));
        }

        username.setText("Currently logged in as: " + state.getUsername());
        watchedList.revalidate();
        watchedList.repaint();
    }

    private List<Movie> prepareMovieList(WatchedListState state) {
        final List<String> titles = state.getWatchedListTitle();
        final List<String> posters = state.getWatchedListUrl();

        final List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), posters.get(i)));
        }
        movies.sort(Comparator.comparing(Movie::getTitle));
        return movies;
    }

    private JPanel createMoviePanel(WatchedListState state, Movie movie) {
        final JPanel moviePanel = new JPanel(new BorderLayout());

        moviePanel.add(createTitleLabel(movie.getTitle()), BorderLayout.NORTH);
        moviePanel.add(createPosterLabel(movie.getPosterUrl()), BorderLayout.CENTER);
        moviePanel.add(createActionButtonsPanel(state, movie.getTitle()), BorderLayout.SOUTH);

        return moviePanel;
    }

    private JLabel createTitleLabel(String title) {
        final JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        return titleLabel;
    }

    private JLabel createPosterLabel(String posterUrl) {
        final JLabel posterLabel = new JLabel("", SwingConstants.CENTER);

        if (posterUrl.isEmpty()) {
            posterLabel.setText("Poster not available.");
        }
        else {
            try {
                final URL url = new URL(posterUrl);
                final BufferedImage image = ImageIO.read(url);
                final Image scaledImage = image.getScaledInstance(Constants.IMAGE_WIDTH,
                        Constants.IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaledImage));
            }
            catch (IOException exception) {
                posterLabel.setText("Poster not available.");
            }
        }
        return posterLabel;
    }

    private JPanel createActionButtonsPanel(WatchedListState state, String movieTitle) {
        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 0));

        final JButton removeButton = createStyledButton("Remove");
        removeButton.addActionListener(evt -> {
            watchedListRemoveController.execute(state.getUsername(), movieTitle);
            JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has been removed from your watched list.");
        });

        final JButton rateButton = createStyledButton("Rate");
        rateButton.addActionListener(evt -> goToRateController.goToRate(state.getUsername(), movieTitle));

        buttonPanel.add(removeButton);
        buttonPanel.add(rateButton);
        return buttonPanel;
    }

    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
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

    public void setWatchedListRemoveController(WatchedListRemoveController controller) {
        this.watchedListRemoveController = controller;
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
