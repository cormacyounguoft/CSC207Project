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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WatchedListView extends JPanel implements ActionListener, PropertyChangeListener {
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

        final JLabel title = new JLabel(watchedListRemoveViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        watchedList = new JPanel();
        scroller = new JScrollPane(watchedList);

        final JPanel buttons = new JPanel();
        cancel = new JButton(watchedListRemoveViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final WatchedListRemoveState currentState = watchedListRemoveViewModel.getState();
                        goToLoggedInViewController.toLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(scroller);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchedListRemoveState state = (WatchedListRemoveState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchedListUrl();
            final List<String> movieTitles = state.getWatchedListTitle();
            watchedList.removeAll();

            for (int i = 0; i < moviePosters.size(); i++) {
                int finalI = i;
                JPanel movieWatched = new JPanel();
                movieWatched.setLayout(new BoxLayout(movieWatched, BoxLayout.Y_AXIS));
                movieWatched.setOpaque(false);

                JLabel title = new JLabel(movieTitles.get(finalI));
                title.setFont(new Font("SansSerif", Font.PLAIN, 16));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel posterLabels = new JLabel();
                posterLabels.setAlignmentX(Component.CENTER_ALIGNMENT);
                if (moviePosters.get(i).isEmpty()) {
                    posterLabels.setText("Poster not available.");
                }
                else {
                    try {
                        URL url = new URL(moviePosters.get(i));
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                        posterLabels.setText("");
                        posterLabels.setIcon(new ImageIcon(scaledImage));

                    } catch (IOException e) {
                        posterLabels.setText("Poster not available.");
                    }
                }

                JButton rate = createStyledButton("Rate");
                rate.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                goToRateController.goToRate(state.getUsername(), movieTitles.get(finalI));
                            }
                        }
                );

                JButton remove = createStyledButton("Remove");
                remove.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                watchedListRemoveController.execute(state.getUsername(), movieTitles.get(finalI));
                                JOptionPane.showMessageDialog(null, "\"" + movieTitles.get(finalI) + "\" has been deleted from your watched list and rated list.");
                            }
                        }
                );

                rate.setAlignmentX(Component.CENTER_ALIGNMENT);
                remove.setAlignmentX(Component.CENTER_ALIGNMENT);

                movieWatched.add(title);
                movieWatched.add(Box.createVerticalStrut(10));
                movieWatched.add(posterLabels);
                movieWatched.add(Box.createVerticalStrut(10));
                movieWatched.add(rate);
                movieWatched.add(Box.createVerticalStrut(10));
                movieWatched.add(remove);
                watchedList.add(movieWatched);
            }
            username.setText("Username: " + state.getUsername());
        }
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


}
