package view;

import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list.WatchedListController;
import interface_adapter.watched_list.WatchedListState;
import interface_adapter.watched_list.WatchedListViewModel;

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

public class WatchedListView extends JPanel implements PropertyChangeListener {

    private final String viewName = "watched list";

    private final WatchedListViewModel watchedListViewModel;
    private WatchedListController watchedListController;
    private GoRateController goToRateController;
    private ToLoggedInViewController goToLoggedInViewController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel watchedList;
    private final JScrollPane scroller;

    public WatchedListView(WatchedListViewModel watchedListViewModel) {
        this.watchedListViewModel = watchedListViewModel;
        this.watchedListViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255)); // Light blue background
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Title Label
        final JLabel title = new JLabel("Watched List", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        this.add(title, BorderLayout.NORTH);

        // Username Section
        username = new JLabel();
        username.setFont(new Font("SansSerif", Font.PLAIN, 16));
        username.setForeground(new Color(0, 51, 102));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        // Watched List Content Panel
        watchedList = new JPanel();
        watchedList.setLayout(new GridLayout(0, 4, 30, 10)); // 4 movies per row, horizontal spacing > vertical spacing
        watchedList.setBackground(new Color(255, 255, 255)); // White background for clarity

        scroller = new JScrollPane(watchedList);
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
            final WatchedListState currentState = watchedListViewModel.getState();
            goToLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchedListState state = (WatchedListState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchedListURL();
            final List<String> movieTitles = state.getWatchedListTitle();
            watchedList.removeAll();

            for (int i = 0; i < moviePosters.size(); i++) {
                JButton posterButton = new JButton();
                posterButton.setHorizontalAlignment(SwingConstants.CENTER);

                if (moviePosters.get(i).isEmpty()) {
                    posterButton.setText("Poster not available.");
                } else {
                    try {
                        URL url = new URL(moviePosters.get(i));
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH); // Larger poster size
                        posterButton.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException e) {
                        posterButton.setText("Poster not available.");
                    }
                }

                int finalI = i;
                posterButton.addActionListener(actionEvent -> {
                    goToRateController.goToRate(state.getUsername(), movieTitles.get(finalI));
                });

                watchedList.add(posterButton);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            watchedList.revalidate();
            watchedList.repaint();
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

    public void setWatchedListController(WatchedListController watchedListController) {
        this.watchedListController = watchedListController;
    }

    public void setGoToRateController(GoRateController goToRateController) {
        this.goToRateController = goToRateController;
    }

    public void setGoToLoggedInViewController(ToLoggedInViewController goToLoggedInViewController) {
        this.goToLoggedInViewController = goToLoggedInViewController;
    }
}
