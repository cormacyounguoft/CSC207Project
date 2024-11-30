package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

/**
 * View for Rated List.
 */
public class RatedListView extends JPanel implements PropertyChangeListener {
    private static final int GRID_RATEDLIST_COLUMNS = 4;
    private static final int GRID_RATEDLIST_HGAP = 30;
    private static final int GRID_RATEDLIST_VGAP = 10;

    private final String viewName = "rated list";
    private final RatedListViewModel ratedListViewModel;
    private RatedListController ratedListController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel ratedList;
    private final JScrollPane scroller;

    public RatedListView(RatedListViewModel ratedListViewModel) {
        this.ratedListViewModel = ratedListViewModel;
        this.ratedListViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B));
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT));

        // Title Label
        final JLabel title = new JLabel("Rated List", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_TYPE, Font.BOLD, Constants.FONT_LARGEST));
        title.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        this.add(title, BorderLayout.NORTH);

        // Username Section
        username = new JLabel();
        username.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        username.setForeground(new Color(Constants.FONT_COLOUR_R, Constants.FONT_COLOUR_G, Constants.FONT_COLOUR_B));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        // Rated List Content Panel
        ratedList = new JPanel();
        ratedList.setLayout(new GridLayout(0, GRID_RATEDLIST_COLUMNS, GRID_RATEDLIST_HGAP, GRID_RATEDLIST_VGAP));
        ratedList.setBackground(new Color(Constants.COLOUR_B, Constants.COLOUR_B, Constants.COLOUR_B));

        scroller = new JScrollPane(ratedList);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(Constants.SCROLLER_WIDTH, Constants.SCROLLER_HEIGHT));
        this.add(scroller, BorderLayout.CENTER);

        // Buttons Panel
        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);

        cancel = createStyledButton("Cancel");
        buttonsPanel.add(cancel);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        cancel.addActionListener(evt -> {
            final RatedListState currentState = ratedListViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final RatedListState state = (RatedListState) evt.getNewValue();
            final Map<String, List<String>> ratings = state.getRating();
            ratedList.removeAll();

            for (Map.Entry<String, List<String>> entry : ratings.entrySet()) {
                final JPanel ratingPanel = createRatingPanel(entry, state.getUsername());
                ratedList.add(ratingPanel);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            ratedList.revalidate();
            ratedList.repaint();
        }
    }

    private JPanel createRatingPanel(Map.Entry<String, List<String>> entry, String currentUsername) {
        final String movieTitle = entry.getKey();
        final List<String> details = entry.getValue();
        final String rating = details.get(0);
        final String posterUrl = details.get(1);

        final JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.Y_AXIS));
        ratingPanel.setOpaque(false);

        ratingPanel.add(createTitleLabel(movieTitle));
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(createPosterLabel(posterUrl));
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(createRatingLabel(rating));
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(createRemoveButton(movieTitle, currentUsername));

        return ratingPanel;
    }

    private JLabel createTitleLabel(String movieTitle) {
        final JLabel titleLabel = new JLabel(movieTitle);
        titleLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    private JLabel createPosterLabel(String posterUrl) {
        final JLabel posterLabel = new JLabel();
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!posterUrl.isEmpty()) {
            try {
                final URL url = new URL(posterUrl);
                final BufferedImage image = ImageIO.read(url);
                final Image scaledImage = image.getScaledInstance(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT,
                        Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaledImage));
            }
            catch (IOException exception) {
                posterLabel.setText("Poster not available.");
            }
        }
        else {
            posterLabel.setText("Poster not available.");
        }
        return posterLabel;
    }

    private JLabel createRatingLabel(String rating) {
        final JLabel ratingLabel = new JLabel("Rating: " + rating);
        ratingLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return ratingLabel;
    }

    private JButton createRemoveButton(String movieTitle, String currentUsername) {
        final JButton removeButton = createStyledButton("Remove");
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeButton.addActionListener(evt -> {
            ratedListController.execute(currentUsername, movieTitle);
            JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has been deleted from your rated list.");
        });
        return removeButton;
    }

    /**
     * Creates a styled button.
     * @param text the text in the styled button.
     * @return The styled button that is created.
     */
    private JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_LARGER));
        button.setBackground(new Color(Constants.BACKGROUND_COLOUR_R, Constants.BACKGROUND_COLOUR_G,
                Constants.BACKGROUND_COLOUR_B));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(Constants.BORDER_COLOUR_R, Constants.BORDER_COLOUR_G,
                Constants.BORDER_COLOUR_B), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setRatedListController(RatedListController ratedListController) {
        this.ratedListController = ratedListController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
