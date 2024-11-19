package view;

import entity.Movie;
import interface_adapter.go_to_rate.GoToRateController;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WatchedListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "watched list";

    private final WatchedListViewModel watchedListViewModel;
    private WatchedListController watchedListController;
    private GoToRateController goToRateController;
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
                JButton posterLabels = new JButton();
                if (moviePosters.get(i).isEmpty()) {
                    posterLabels.setText("Poster not available.");
                }
                else {
                    try {
                        URL url = new URL(moviePosters.get(i));
                        BufferedImage image = ImageIO.read(url);
                        posterLabels.setText("");
                        posterLabels.setIcon(new ImageIcon(image));
                        int finalI = i;
                        posterLabels.addActionListener(
                                new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                        goToRateController.goToRate(state.getUsername(), movieTitles.get(finalI));
                                    }
                                }
                        );
                    } catch (IOException e) {
                        posterLabels.setText("Poster not available.");
                    }
                }
                watchedList.add(posterLabels);
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

    public void setGoToRateController(GoToRateController goToRateController) {
        this.goToRateController = goToRateController;
    }

    public void setGoToLoggedInViewController(ToLoggedInViewController goToLoggedInViewController) {
        this.goToLoggedInViewController = goToLoggedInViewController;
    }
}
