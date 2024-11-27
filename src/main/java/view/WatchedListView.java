package view;

import interface_adapter.go_to_rate.GoRateController;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list.WatchedListController;
import interface_adapter.watched_list.WatchedListState;
import interface_adapter.watched_list.WatchedListViewModel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WatchedListView extends JPanel implements ActionListener, PropertyChangeListener {
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

        final JLabel title = new JLabel(watchedListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        watchedList = new JPanel();
        scroller = new JScrollPane(watchedList);

        final JPanel buttons = new JPanel();
        cancel = new JButton(watchedListViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final WatchedListState currentState = watchedListViewModel.getState();
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
            final WatchedListState state = (WatchedListState) evt.getNewValue();
            final List<String> moviePosters = state.getWatchedListURL();
            final List<String> movieTitles = state.getWatchedListTitle();
            watchedList.removeAll();

            for (int i = 0; i < moviePosters.size(); i++) {
                int finalI = i;
                JPanel movieWatched = new JPanel();
                movieWatched.setPreferredSize(new Dimension(320, 500));
                Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border, 2 pixels thick
                movieWatched.setBorder(BorderFactory.createTitledBorder(border));
                movieWatched.setLayout(new BoxLayout(movieWatched, BoxLayout.Y_AXIS));

                JLabel title = new JLabel(movieTitles.get(finalI));

                JLabel posterLabels = new JLabel();
                if (moviePosters.get(i).isEmpty()) {
                    posterLabels.setText("Poster not available.");
                }
                else {
                    try {
                        URL url = new URL(moviePosters.get(i));
                        BufferedImage image = ImageIO.read(url);
                        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                        posterLabels.setText("");
                        posterLabels.setIcon(new ImageIcon(scaledImage));

                    } catch (IOException e) {
                        posterLabels.setText("Poster not available.");
                    }
                }

                JButton rate = new JButton("Rate");
                rate.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                goToRateController.goToRate(state.getUsername(), movieTitles.get(finalI));
                            }
                        }
                );

                JButton remove = new JButton("Remove");
                remove.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                watchedListController.execute(state.getUsername(), movieTitles.get(finalI));
                            }
                        }
                );

                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                posterLabels.setAlignmentX(Component.CENTER_ALIGNMENT);
                rate.setAlignmentX(Component.CENTER_ALIGNMENT);
                remove.setAlignmentX(Component.CENTER_ALIGNMENT);

                movieWatched.add(title);
                movieWatched.add(posterLabels);
                movieWatched.add(rate);
                movieWatched.add(remove);
                watchedList.add(movieWatched);
            }
            username.setText("Username: " + state.getUsername());
        }
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
