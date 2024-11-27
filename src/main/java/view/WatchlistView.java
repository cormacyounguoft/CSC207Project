package view;

import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistState;
import interface_adapter.watchlist.WatchlistViewModel;

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

public class WatchlistView extends JPanel implements PropertyChangeListener {
    private final String viewName = "watchlist";

    private final WatchlistViewModel watchlistViewModel;
    private WatchlistController watchlistController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel watchlist;
    private final JScrollPane scroller;

    public WatchlistView(WatchlistViewModel watchlistViewModel) {
        this.watchlistViewModel = watchlistViewModel;
        this.watchlistViewModel.addPropertyChangeListener(this);

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
            final WatchlistState currentState = watchlistViewModel.getState();
            toLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchlistState state = (WatchlistState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchlistURL();
            watchlist.removeAll();

            for (String poster : moviePosters) {
                JLabel posterLabel = new JLabel();
                posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                if (poster.isEmpty()) {
                    posterLabel.setText("Poster not available.");
                } else {
                    try {
                        URL url = new URL(poster);
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH); // Larger poster size
                        posterLabel.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException e) {
                        posterLabel.setText("Poster not available.");
                    }
                }

                watchlist.add(posterLabel);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            watchlist.revalidate();
            watchlist.repaint();
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

    public void setWatchlistController(WatchlistController watchlistController) {
        this.watchlistController = watchlistController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
