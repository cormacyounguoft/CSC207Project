package view;

import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistState;
import interface_adapter.watchlist.WatchlistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WatchlistView  extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "watchlist";

    private final WatchlistViewModel watchlistViewModel;
    private WatchlistController watchlistController;

    private final JButton cancel;

    private final JLabel username;
    private final JLabel watchlist;

    public WatchlistView(WatchlistViewModel watchlistViewModel) {
        this.watchlistViewModel = watchlistViewModel;
        this.watchlistViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(watchlistViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        watchlist = new JLabel();

        final JPanel buttons = new JPanel();
        cancel = new JButton(watchlistViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final WatchlistState currentState = watchlistViewModel.getState();
                        watchlistController.switchToLoggedInView(currentState.getUsername());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
        this.add(watchlist);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchlistState state = (WatchlistState) evt.getNewValue();
            watchlist.setText(state.getWatchlist().toString());
            username.setText("Username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchlistController(WatchlistController watchlistController) {
        this.watchlistController = watchlistController;
    }
}
