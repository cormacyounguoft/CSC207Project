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
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title and Release Date
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);

        movieTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        movieTitle.setForeground(new Color(0, 51, 102)); // Dark blue
        titlePanel.add(movieTitle);

        movieReleaseDate.setFont(new Font("SansSerif", Font.PLAIN, 18));
        movieReleaseDate.setForeground(new Color(0, 51, 102));
        titlePanel.add(movieReleaseDate);

        this.add(titlePanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setOpaque(false);

        // Poster
        moviePoster.setHorizontalAlignment(SwingConstants.CENTER);
        moviePoster.setVerticalAlignment(SwingConstants.TOP);
        moviePoster.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1));
        contentPanel.add(moviePoster, BorderLayout.WEST);

        // Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout(0, 10)); // Vertical alignment with spacing
        detailsPanel.setOpaque(false);

        // Highlighted Details
        JPanel highlightedDetailsPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Horizontal side-by-side
        highlightedDetailsPanel.setOpaque(false);
        highlightedDetailsPanel.add(labelFormatter("Rotten Tomatoes: ", movieRottenTomatoes));
        highlightedDetailsPanel.add(labelFormatter("Genres: ", movieGenre));
        detailsPanel.add(highlightedDetailsPanel, BorderLayout.NORTH);

        // Description
        movieDescription.setLineWrap(true);
        movieDescription.setWrapStyleWord(true);
        movieDescription.setEditable(false);
        movieDescription.setFont(new Font("SansSerif", Font.PLAIN, 16));
        movieDescription.setForeground(new Color(0, 51, 102));
        movieDescription.setBackground(new Color(240, 248, 255)); // Match background
        detailsPanel.add(new JScrollPane(movieDescription), BorderLayout.CENTER);

        // Other Details
        JPanel otherDetailsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
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
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(new Color(0, 51, 102));

        valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        valueLabel.setForeground(new Color(0, 51, 102));

        panel.add(label, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }

    private JButton buttonFactory(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 22));
        button.setBackground(new Color(93, 186, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(220, 50));
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
}
