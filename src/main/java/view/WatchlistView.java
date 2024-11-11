package view;

import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistState;
import interface_adapter.watchlist.WatchlistViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WatchlistView  extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "watchlist";

    private final WatchlistViewModel watchlistViewModel;
    private WatchlistController watchlistController;

    private final JButton cancel;

    private final JLabel username;
    private final JPanel watchlist;
    private final JScrollPane scroller;

    public WatchlistView(WatchlistViewModel watchlistViewModel) {
        this.watchlistViewModel = watchlistViewModel;
        this.watchlistViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(watchlistViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        watchlist = new JPanel();
        scroller = new JScrollPane(watchlist);

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
        this.add(scroller);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WatchlistState state = (WatchlistState) evt.getNewValue();
            final List<String> posters = state.getWatchlist().getPosters();
            watchlist.removeAll();

            for (String poster: posters) {
                JLabel posterLabels = new JLabel();
                if (poster.isEmpty()) {
                    posterLabels.setText("Poster not available.");
                }
                else {
                    try {
                        URL url = new URL(poster);
                        BufferedImage image = ImageIO.read(url);
                        posterLabels.setText("");
                        posterLabels.setIcon(new ImageIcon(image));

                    } catch (IOException e) {
                        System.out.println("Poster not available");
                    }
                }
                watchlist.add(posterLabels);
            }
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
