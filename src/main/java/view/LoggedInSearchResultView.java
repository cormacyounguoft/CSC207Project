package view;

import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoggedInSearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int BORDER_LAYOUT_FOR_LOGGEDIN_HGAP = 20;
    private static final int BORDER_LAYOUT_FOR_LOGGEDIN_VGAP = 10;
    private static final int BORDER_LAYOUT_FOR_LOGGEDIN_DETAILS_HGAP = 10;
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 50;

    private final String viewName = "logged in search result";
    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;

    private LoggedInSearchResultController loggedInSearchResultController;
    private AddToWatchlistController addToWatchlistController;
    private AddToWatchedListController addToWatchedListController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton addToWatchlist;
    private final JButton addToWatchedList;
    private final JButton cancel;

    private final JLabel movieTitle = new JLabel();
    private final JLabel movieReleaseDate = new JLabel();
    private final JLabel movieRottenTomatoes = new JLabel();
    private final JLabel movieGenre = new JLabel();
    private final JTextArea movieDescription = new JTextArea();
    private final JLabel movieActors = new JLabel();
    private final JLabel movieDirector = new JLabel();
    private final JLabel moviePoster = new JLabel();

    public LoggedInSearchResultView(LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInSearchResultViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Light blue background
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT)); // Padding around the panel

        // Title and Release Date
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);

        movieTitle.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        movieTitle.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B)); // Dark blue
        titlePanel.add(movieTitle);

        movieReleaseDate.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_MEDIUM));
        movieReleaseDate.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        titlePanel.add(movieReleaseDate);

        this.add(titlePanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(BORDER_LAYOUT_FOR_LOGGEDIN_HGAP, 0));
        contentPanel.setOpaque(false);

        // Poster
        moviePoster.setHorizontalAlignment(SwingConstants.CENTER);
        moviePoster.setVerticalAlignment(SwingConstants.TOP);
        moviePoster.setBorder(BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G, Constants.LINE_BORDER_B), Constants.LINE_BORDER_THICKNESS));
        contentPanel.add(moviePoster, BorderLayout.WEST);

        // Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout(0, BORDER_LAYOUT_FOR_LOGGEDIN_VGAP)); // Vertical alignment with spacing
        detailsPanel.setOpaque(false);

        // Highlighted Details
        JPanel highlightedDetailsPanel = new JPanel(new GridLayout(1, 2, BORDER_LAYOUT_FOR_LOGGEDIN_DETAILS_HGAP, 0)); // Horizontal side-by-side
        highlightedDetailsPanel.setOpaque(false);
        highlightedDetailsPanel.add(labelFormatter("Rotten Tomatoes: ", movieRottenTomatoes));
        highlightedDetailsPanel.add(labelFormatter("Genres: ", movieGenre));
        detailsPanel.add(highlightedDetailsPanel, BorderLayout.NORTH);

        // Description
        movieDescription.setLineWrap(true);
        movieDescription.setWrapStyleWord(true);
        movieDescription.setEditable(false);
        movieDescription.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_SMALLER));
        movieDescription.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        movieDescription.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Match background
        detailsPanel.add(new JScrollPane(movieDescription), BorderLayout.CENTER);

        // Other Details
        JPanel otherDetailsPanel = new JPanel(new GridLayout(2, 1, Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        otherDetailsPanel.setOpaque(false);
        otherDetailsPanel.add(labelFormatter("Actors: ", movieActors));
        otherDetailsPanel.add(labelFormatter("Directors: ", movieDirector));
        detailsPanel.add(otherDetailsPanel, BorderLayout.SOUTH);

        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        this.add(contentPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false);

        addToWatchlist = buttonFactory("Add to Watchlist");
        buttonsPanel.add(addToWatchlist);

        addToWatchedList = buttonFactory("Add to Watched List");
        buttonsPanel.add(addToWatchedList);

        cancel = buttonFactory("Cancel");
        buttonsPanel.add(cancel);

        addToWatchlist.addActionListener(evt -> {
            final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
            addToWatchlistController.execute(currentState.getUsername(), currentState.getTitle());
            JOptionPane.showMessageDialog(null, "\"" + currentState.getTitle() + "\" has been added to your watchlist.");
        });

        addToWatchedList.addActionListener(evt -> {
            final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
            addToWatchedListController.execute(currentState.getUsername(), currentState.getTitle());
            JOptionPane.showMessageDialog(null, "\"" + currentState.getTitle() + "\" has been added to your watched list.");
        });

        cancel.addActionListener(evt -> {
            final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JPanel labelFormatter(String labelText, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        valueLabel.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_SMALLER));
        valueLabel.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        panel.add(label, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }

    private JButton buttonFactory(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInSearchResultState state = (LoggedInSearchResultState) evt.getNewValue();
            movieTitle.setText(state.getTitle());
            movieReleaseDate.setText("Release Date: " + state.getReleaseDate());
            movieRottenTomatoes.setText(state.getRottenTomatoes());
            movieGenre.setText(state.getGenre().replaceAll("[\\[\\]]", ""));
            movieDescription.setText(state.getDescription());
            movieActors.setText(state.getActors().replaceAll("[\\[\\]]", ""));
            movieDirector.setText(state.getDirector().replaceAll("[\\[\\]]", ""));
            setMoviePoster(state.getPosterLink());
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
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setMoviePoster(String poster) {
        if (Objects.equals(poster, "Poster not available.")) {
            moviePoster.setText("Poster not available.");
            moviePoster.setIcon(null);
        } else {
            try {
                URL url = new URL(poster);
                BufferedImage image = ImageIO.read(url);
                moviePoster.setText("");
                moviePoster.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                moviePoster.setText("Poster not available.");
                moviePoster.setIcon(null);
            }
        }
    }
}
