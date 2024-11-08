package view;

import interface_adapter.watched_list.WatchedListController;
import interface_adapter.watched_list.WatchedListState;
import interface_adapter.watched_list.WatchedListViewModel;

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

    private final JButton cancel;

    private final JLabel username;
    private final JLabel watchedList;

    public WatchedListView(WatchedListViewModel watchedListViewModel) {
        this.watchedListViewModel = watchedListViewModel;
        this.watchedListViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(watchedListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        watchedList = new JLabel();

        final JPanel buttons = new JPanel();
        cancel = new JButton(watchedListViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final WatchedListState currentState = watchedListViewModel.getState();
                        watchedListController.switchToLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(watchedList);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchedListState state = (WatchedListState) evt.getNewValue();
            watchedList.setText(state.getWatchedList().toString());
            username.setText("Username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchedListController(WatchedListController watchedListController) {
        this.watchedListController = watchedListController;
    }
}
