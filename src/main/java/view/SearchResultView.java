package view;

import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

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

/**
 * The View for the Search Result Use Case.
 */
public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search result";
    private final SearchResultViewModel searchResultViewModel;

    private SearchResultController searchResultController;
    private ToHomeViewController toHomeViewController;

    private final JButton toHome;

    private final JLabel movieTitle = new JLabel();
    private final JLabel movieReleaseDate = new JLabel();
    private final JLabel movieRottenTomatoes = new JLabel();
    private final JLabel movieGenre = new JLabel();
    private final JTextArea movieDescription = new JTextArea();
    private final JLabel movieActors = new JLabel();
    private final JLabel movieDirector = new JLabel();
    private final JLabel moviePoster = new JLabel();


    public SearchResultView(SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;
        this.searchResultViewModel.addPropertyChangeListener(this);

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
        JPanel contentPanel = new JPanel(new BorderLayout(Constants.CONTENT_HGAP, 0));
        contentPanel.setOpaque(false);

        // Poster
        moviePoster.setHorizontalAlignment(SwingConstants.CENTER);
        moviePoster.setVerticalAlignment(SwingConstants.TOP);
        moviePoster.setBorder(BorderFactory.createLineBorder(new Color(Constants.LINE_BORDER_R, Constants.LINE_BORDER_G, Constants.LINE_BORDER_B), 1));
        contentPanel.add(moviePoster, BorderLayout.WEST);

        // Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout(0, Constants.DETAILS_VGAP)); // Vertical alignment with spacing
        detailsPanel.setOpaque(false);

        // Highlighted Details
        JPanel highlightedDetailsPanel = new JPanel(new GridLayout(1, 2, Constants.HIGHLIGHTED_DETAILS_HGAP, 0)); // Horizontal side-by-side
        highlightedDetailsPanel.setOpaque(false);
        highlightedDetailsPanel.add(labelFormatter("Rotten Tomatoes: ", movieRottenTomatoes));
        highlightedDetailsPanel.add(labelFormatter("Genres: ", movieGenre));
        detailsPanel.add(highlightedDetailsPanel, BorderLayout.NORTH);

        // Description (Multiline Wrapping)
        movieDescription.setLineWrap(true);
        movieDescription.setWrapStyleWord(true);
        movieDescription.setEditable(false);
        movieDescription.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        movieDescription.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        movieDescription.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Match background
        detailsPanel.add(new JScrollPane(movieDescription), BorderLayout.CENTER);

        // Other Details
        JPanel otherDetailsPanel = new JPanel(new GridLayout(2, 1, Constants.OTHER_DETAILS_GAP, Constants.OTHER_DETAILS_GAP));
        otherDetailsPanel.setOpaque(false);
        otherDetailsPanel.add(labelFormatter("Actors: ", movieActors));
        otherDetailsPanel.add(labelFormatter("Directors: ", movieDirector));
        detailsPanel.add(otherDetailsPanel, BorderLayout.SOUTH);

        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        this.add(contentPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false);

        toHome = buttonFactory("Home");
        buttonsPanel.add(toHome);

        toHome.addActionListener(evt -> toHomeViewController.toHomeView());
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JPanel labelFormatter(String labelText, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout(Constants.LABEL_BORDER_LAYOUT, Constants.LABEL_BORDER_LAYOUT));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_SMALLER));
        label.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        valueLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        valueLabel.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));

        panel.add(label, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }

    private JButton buttonFactory(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G, Constants.BACKGROUND_COLOUR_B)); // Pastel blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G, Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)); // Consistent size
        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SearchResultState state = (SearchResultState) evt.getNewValue();

            movieTitle.setText(state.getTitle());
            movieReleaseDate.setText("Release Date: " + state.getReleaseDate());
            movieRottenTomatoes.setText(state.getRottenTomatoes());
            movieGenre.setText(state.getGenre().replaceAll("[\\[\\]]", "")); // Remove brackets
            movieDescription.setText(state.getDescription());
            movieActors.setText(state.getActors().replaceAll("[\\[\\]]", "")); // Remove brackets
            movieDirector.setText(state.getDirector().replaceAll("[\\[\\]]", "")); // Remove brackets
            setMoviePoster(state.getPoster());
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

    public void setSearchResultController(SearchResultController controller) {
        this.searchResultController = controller;
    }

    public void setToHomeViewController(ToHomeViewController toHomeViewController) {
        this.toHomeViewController = toHomeViewController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
