package view;

import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RatedListView extends JPanel implements PropertyChangeListener {

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
        this.setBackground(new Color(Constants.COLOUR_R, Constants.COLOUR_G, Constants.COLOUR_B)); // Light blue background
        this.setBorder(new EmptyBorder(Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT,
                Constants.MAIN_BORDER_LAYOUT, Constants.MAIN_BORDER_LAYOUT)); // Padding around the panel

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
        ratedList.setLayout(new GridLayout(0, 4, 30, 10)); // 4 movies per row, horizontal spacing > vertical spacing
        ratedList.setBackground(new Color(Constants.COLOUR_B, Constants.COLOUR_B, Constants.COLOUR_B)); // White background for clarity

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
                JPanel ratingPanel = createRatingPanel(entry, state.getUsername());
                ratedList.add(ratingPanel);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            ratedList.revalidate();
            ratedList.repaint();
        }
    }

    private JPanel createRatingPanel(Map.Entry<String, List<String>> entry, String username) {
        String movieTitle = entry.getKey();
        List<String> details = entry.getValue();
        String rating = details.get(0);
        String posterURL = details.get(1);

        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.Y_AXIS));
        ratingPanel.setOpaque(false);

        // Movie Title Label
        JLabel titleLabel = new JLabel(movieTitle);
        titleLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Movie Poster
        JLabel posterLabel = new JLabel();
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!posterURL.isEmpty()) {
            try {
                URL url = new URL(posterURL);
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, Image.SCALE_SMOOTH); // Resize poster
                posterLabel.setIcon(new ImageIcon(scaledImage));
            } catch (IOException e) {
                posterLabel.setText("Poster not available.");
            }
        } else {
            posterLabel.setText("Poster not available.");
        }

        // Rating Label
        JLabel ratingLabel = new JLabel("Rating: " + rating);
        ratingLabel.setFont(new Font(Constants.FONT_TYPE, Font.PLAIN, Constants.FONT_SMALLER));
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Remove Button
        JButton removeButton = createStyledButton("Remove");
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeButton.addActionListener(evt -> {
            ratedListController.execute(username, movieTitle);
            JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has been deleted from your rated list.");
        });

        // Add components to the panel
        ratingPanel.add(titleLabel);
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(posterLabel);
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(ratingLabel);
        ratingPanel.add(Box.createVerticalStrut(Constants.VERTICAL_STRUT));
        ratingPanel.add(removeButton);

        return ratingPanel;
    }

    /**
     * Creates a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, Constants.FONT_LARGER));
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

    public void setRatedListController(RatedListController ratedListController) {
        this.ratedListController = ratedListController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
