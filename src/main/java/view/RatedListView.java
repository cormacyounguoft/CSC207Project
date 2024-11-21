package view;

import entity.Movie;
import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.watched_list.WatchedListState;

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

import java.util.Map;

public class RatedListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "rated list";
    private final RatedListViewModel ratedListViewModel;
    private RatedListController ratedListController;
    private final JButton cancel;

    private final JLabel username;
    private final JPanel ratedList;
    private final JScrollPane scroller;

    public RatedListView(RatedListViewModel ratedListViewModel){
        this.ratedListViewModel = ratedListViewModel;
        this.ratedListViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ratedListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        ratedList = new JPanel();
        scroller = new JScrollPane(ratedList);

        final JPanel buttons = new JPanel();
        cancel = new JButton(ratedListViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final RatedListState currentState = ratedListViewModel.getState();
                        ratedListController.switchToLoggedInView(currentState.getUsername());
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
            final RatedListState state = (RatedListState) evt.getNewValue();
            final Map<String, Integer> ratings = state.getRating();
            ratedList.removeAll();

            for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                JButton rating= new JButton(key + " : " + value);

                ratedList.add(rating);
                }

            username.setText("Username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setRatedListController(RatedListController ratedListController){
        this.ratedListController = ratedListController;
    }
}
