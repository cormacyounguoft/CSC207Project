package view;

import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list_remove.WatchedListRemoveController;
import interface_adapter.watched_list_remove.WatchedListRemoveState;
import interface_adapter.watched_list_remove.WatchedListRemoveViewModel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WatchedListView extends JPanel implements PropertyChangeListener {
    private final String viewName = "watched list";

    private final WatchedListRemoveViewModel watchedListRemoveViewModel;
    private WatchedListRemoveController watchedListRemoveController;
    private GoRateController goToRateController;
    private ToLoggedInViewController goToLoggedInViewController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel watchedList;
    private final JScrollPane scroller;

    public WatchedListView(WatchedListRemoveViewModel watchedListRemoveViewModel) {
        this.watchedListRemoveViewModel = watchedListRemoveViewModel;
        this.watchedListRemoveViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(240, 248, 255));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        final JLabel title = new JLabel("Watched List", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        this.add(title, BorderLayout.NORTH);

        username = new JLabel();
        username.setFont(new Font("SansSerif", Font.PLAIN, 16));
        username.setForeground(new Color(0, 51, 102));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(username, BorderLayout.SOUTH);

        watchedList = new JPanel();
        watchedList.setLayout(new GridLayout(0, 4, 0, 10)); // 4 movies per row
        watchedList.setBackground(new Color(255, 255, 255)); // White background

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
            final WatchedListRemoveState currentState = watchedListRemoveViewModel.getState();
            goToLoggedInViewController.toLoggedInView(currentState.getUsername());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchedListRemoveState state = (WatchedListRemoveState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchedListUrl();
            final List<String> movieTitles = state.getWatchedListTitle();

            watchedList.removeAll();

            for (int i = 0; i < movieTitles.size(); i++) {
                String poster = moviePosters.get(i);
                String movieTitle = movieTitles.get(i);

                JPanel moviePanel = new JPanel(new BorderLayout());
                JLabel titleLabel = new JLabel(movieTitle, SwingConstants.CENTER);
                titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
                moviePanel.add(titleLabel, BorderLayout.NORTH);

                JLabel posterLabel = new JLabel();
                posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                if (poster.isEmpty()) {
                    posterLabel.setText("Poster not available.");
                } else {
                    try {
                        URL url = new URL(poster);
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                        posterLabel.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException e) {
                        posterLabel.setText("Poster not available.");
                    }
                }

                moviePanel.add(posterLabel, BorderLayout.CENTER);

                // Add buttons for each movie
                JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 0));
                JButton rateButton = createStyledButton("Rate");
                rateButton.addActionListener(evt1 -> goToRateController.goToRate(state.getUsername(), movieTitle));

                JButton removeButton = createStyledButton("Remove");
                removeButton.addActionListener(evt1 -> {
                    watchedListRemoveController.execute(state.getUsername(), movieTitle);
                    JOptionPane.showMessageDialog(null, "\"" + movieTitle + "\" has been removed from your watched list.");
                });

                buttonPanel.add(rateButton);
                buttonPanel.add(removeButton);
                moviePanel.add(buttonPanel, BorderLayout.SOUTH);

                watchedList.add(moviePanel);
            }

            username.setText("Currently logged in as: " + state.getUsername());
            watchedList.revalidate();
            watchedList.repaint();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 22));
        button.setBackground(new Color(93, 186, 255)); // Pastel blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(124, 183, 205), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchedListController(WatchedListRemoveController watchedListRemoveController) {
        this.watchedListRemoveController = watchedListRemoveController;
    }

    public void setGoToRateController(GoRateController goToRateController) {
        this.goToRateController = goToRateController;
    }

    public void setGoToLoggedInViewController(ToLoggedInViewController goToLoggedInViewController) {
        this.goToLoggedInViewController = goToLoggedInViewController;
    }
}
