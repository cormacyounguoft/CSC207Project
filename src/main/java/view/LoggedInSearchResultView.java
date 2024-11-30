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
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

/**
 * View for Logged In Search Result.
 */
public class LoggedInSearchResultView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int BORDER_LAYOUT_HGAP = 20;
    private static final int BORDER_LAYOUT_VGAP = 10;
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 50;

    private final String viewName = "logged in search result";
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;

    private LoggedInSearchResultController loggedInSearchResultController;
    private AddToWatchlistController addToWatchlistController;
    private AddToWatchedListController addToWatchedListController;
    private ToLoggedInViewController toLoggedInViewController;

    private JButton addToWatchlistButton;
    private JButton addToWatchedListButton;
    private JButton cancelButton;

    private final JLabel movieTitleLabel = new JLabel();
    private final JLabel movieReleaseDateLabel = new JLabel();
    private final JLabel movieRottenTomatoesLabel = new JLabel();
    private final JLabel movieGenreLabel = new JLabel();
    private final JTextArea movieDescriptionArea = new JTextArea();
    private final JLabel movieActorsLabel = new JLabel();
    private final JLabel movieDirectorLabel = new JLabel();
    private final JLabel moviePosterLabel = new JLabel();

    public LoggedInSearchResultView(LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInSearchResultViewModel.addPropertyChangeListener(this);

        setupLayout();
        setupTitleSection();
        setupContentSection();
        setupButtonsSection();
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout(BORDER_LAYOUT_HGAP, BORDER_LAYOUT_VGAP));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(BorderFactory.createEmptyBorder(Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
    }

    private void setupTitleSection() {
        final JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);

        configureLabel(movieTitleLabel, Constants.FONT_LARGEST, Font.BOLD);
        configureLabel(movieReleaseDateLabel, Constants.FONT_MEDIUM, Font.PLAIN);

        titlePanel.add(movieTitleLabel);
        titlePanel.add(movieReleaseDateLabel);

        this.add(titlePanel, BorderLayout.NORTH);
    }

    private void setupContentSection() {
        final JPanel contentPanel = new JPanel(new BorderLayout(Constants.CONTENT_HGAP, 0));
        contentPanel.setOpaque(false);

        configurePoster();
        contentPanel.add(moviePosterLabel, BorderLayout.WEST);

        final JPanel detailsPanel = setupDetailsPanel();
        contentPanel.add(detailsPanel, BorderLayout.CENTER);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel setupDetailsPanel() {
        final JPanel detailsPanel = new JPanel(new BorderLayout(0, Constants.DETAILS_VGAP));
        detailsPanel.setOpaque(false);

        final JPanel highlightedDetailsPanel = createHighlightedDetails();
        detailsPanel.add(highlightedDetailsPanel, BorderLayout.NORTH);

        configureMovieDescription();
        detailsPanel.add(new JScrollPane(movieDescriptionArea), BorderLayout.CENTER);

        final JPanel otherDetailsPanel = createOtherDetails();
        detailsPanel.add(otherDetailsPanel, BorderLayout.SOUTH);

        return detailsPanel;
    }

    private JPanel createHighlightedDetails() {
        final JPanel panel = new JPanel(new GridLayout(1, 2, Constants.HIGHLIGHTED_DETAILS_HGAP, 0));
        panel.setOpaque(false);
        panel.add(createLabeledField("Rotten Tomatoes: ", movieRottenTomatoesLabel));
        panel.add(createLabeledField("Genres: ", movieGenreLabel));
        return panel;
    }

    private JPanel createOtherDetails() {
        final JPanel panel = new JPanel(new GridLayout(2, 1, Constants.OTHER_DETAILS_GAP,
                Constants.OTHER_DETAILS_GAP));
        panel.setOpaque(false);
        panel.add(createLabeledField("Actors: ", movieActorsLabel));
        panel.add(createLabeledField("Directors: ", movieDirectorLabel));
        return panel;
    }

    private void configureMovieDescription() {
        movieDescriptionArea.setLineWrap(true);
        movieDescriptionArea.setWrapStyleWord(true);
        movieDescriptionArea.setEditable(false);
        movieDescriptionArea.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        movieDescriptionArea.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G,
                Constants.FONT_COLOUR_B));
        movieDescriptionArea.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
    }

    private void setupButtonsSection() {
        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false);

        addToWatchlistButton = createButton("Add to Watchlist", evt -> handleAddToWatchlist());
        addToWatchedListButton = createButton("Add to Watched List", evt -> handleAddToWatchedList());
        cancelButton = createButton("Cancel", evt -> handleCancel());

        buttonsPanel.add(addToWatchlistButton);
        buttonsPanel.add(addToWatchedListButton);
        buttonsPanel.add(cancelButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void configurePoster() {
        moviePosterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moviePosterLabel.setVerticalAlignment(SwingConstants.TOP);
        moviePosterLabel.setBorder(BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R,
                Constants.LINE_BORDER_G, Constants.LINE_BORDER_B), Constants.LINE_BORDER_THICKNESS));
    }

    private void handleAddToWatchlist() {
        final LoggedInSearchResultState state = loggedInSearchResultViewModel.getState();
        addToWatchlistController.execute(state.getUsername(), state.getTitle());
        JOptionPane.showMessageDialog(this, "\"" + state.getTitle() + "\" has been added to your watchlist.");
    }

    private void handleAddToWatchedList() {
        final LoggedInSearchResultState state = loggedInSearchResultViewModel.getState();
        addToWatchedListController.execute(state.getUsername(), state.getTitle());
        JOptionPane.showMessageDialog(this, "\"" + state.getTitle() + "\" has been added to your watched list.");
    }

    private void handleCancel() {
        final LoggedInSearchResultState state = loggedInSearchResultViewModel.getState();
        toLoggedInViewController.toLoggedInView(state.getUsername());
    }

    private JPanel createLabeledField(String labelText, JLabel valueLabel) {
        final JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        final JLabel label = new JLabel(labelText);
        configureLabel(label, Constants.FONT_SMALLER, Font.BOLD);

        configureLabel(valueLabel, Constants.FONT_SMALLER, Font.PLAIN);

        panel.add(label, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createButton(String text, ActionListener listener) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G,
                Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R,
                Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(listener);
        return button;
    }

    private void configureLabel(JLabel label, int fontSize, int fontStyle) {
        label.setFont(new Font(Constants.FONT_TYPE, fontStyle, fontSize));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final LoggedInSearchResultState state = (LoggedInSearchResultState) evt.getNewValue();
            updateFields(state);
        }
    }

    private void updateFields(LoggedInSearchResultState state) {
        movieTitleLabel.setText(state.getTitle());
        movieReleaseDateLabel.setText("Release Date: " + state.getReleaseDate());
        movieRottenTomatoesLabel.setText(state.getRottenTomatoes());
        movieGenreLabel.setText(state.getGenre().replaceAll(Constants.BRACKETS_REGEX, ""));
        movieDescriptionArea.setText(state.getDescription());
        movieActorsLabel.setText(state.getActors().replaceAll(Constants.BRACKETS_REGEX, ""));
        movieDirectorLabel.setText(state.getDirector().replaceAll(Constants.BRACKETS_REGEX, ""));
        setMoviePoster(state.getPosterLink());
    }

    /**
     * Sets the movie poster.
     *
     * @param poster the URL of the movie poster
     */
    public void setMoviePoster(String poster) {
        if (Objects.equals(poster, Constants.NO_POSTER)) {
            moviePosterLabel.setText(Constants.NO_POSTER);
            moviePosterLabel.setIcon(null);
        }
        else {
            try {
                final URL url = new URL(poster);
                final BufferedImage image = ImageIO.read(url);
                moviePosterLabel.setText("");
                moviePosterLabel.setIcon(new ImageIcon(image));
            }
            catch (IOException exception) {
                moviePosterLabel.setText(Constants.NO_POSTER);
                moviePosterLabel.setIcon(null);
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInSearchResultController(LoggedInSearchResultController controller) {
        this.loggedInSearchResultController = controller;
    }

    public void setAddToWatchlistController(AddToWatchlistController controller) {
        this.addToWatchlistController = controller;
    }

    public void setAddToWatchedListController(AddToWatchedListController controller) {
        this.addToWatchedListController = controller;
    }

    public void setToLoggedInViewController(ToLoggedInViewController controller) {
        this.toLoggedInViewController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click: " + evt.getActionCommand());
    }
}
