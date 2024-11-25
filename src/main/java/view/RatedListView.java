package view;

import interface_adapter.ratedList.RatedListController;
import interface_adapter.ratedList.RatedListState;
import interface_adapter.ratedList.RatedListViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;
import interface_adapter.watched_list.WatchedListState;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Map;

public class RatedListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "rated list";
    private final RatedListViewModel ratedListViewModel;
    private RatedListController ratedListController;
    private ToLoggedInViewController toLoggedInViewController;
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
                        toLoggedInViewController.toLoggedInView(currentState.getUsername());
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
            final Map<String, List<String>> ratings = state.getRating();
            ratedList.removeAll();

            for (Map.Entry<String,  List<String>> entry : ratings.entrySet()) {
                JPanel rating = getjPanel(entry);
                rating.setPreferredSize(new Dimension(320, 500));
                Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border, 2 pixels thick
                rating.setBorder(BorderFactory.createTitledBorder(border));


                ratedList.add(rating);
                }

            username.setText("Username: " + state.getUsername());
        }
    }

    private JPanel getjPanel(Map.Entry<String, List<String>> entry) {
        String key = entry.getKey();
        List<String> value = entry.getValue();
        String ratingNum = value.get(0);
        String poster = value.get(1);
        JPanel rating = new JPanel();
        rating.setLayout(new BoxLayout(rating, BoxLayout.Y_AXIS));
        JLabel textLabel = new JLabel(key);
        JLabel textLabel2 = new JLabel(ratingNum);
        JLabel textLabel1 = new JLabel(ratingNum);
        JButton remove = new JButton("Remove");
        textLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        remove.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        remove.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final RatedListState state = ratedListViewModel.getState();
                        ratedListController.execute(state.getUsername(), key);
                    }
                }
        );


        rating.add(textLabel);
        rating.add(Box.createVerticalStrut(10));
        if (poster.isEmpty()) {
            textLabel2.setText("Poster not available.");
        }
        else {
            try {
                URL url = new URL(poster);
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                textLabel2.setText("");
                textLabel2.setIcon(new ImageIcon(scaledImage));

            } catch (IOException e) {
                textLabel2.setText("Poster not available.");
            }
        }
        rating.add(textLabel2);
        rating.add(Box.createVerticalStrut(10));


        rating.add(textLabel1);
        rating.add(Box.createVerticalStrut(10));

        rating.add(remove);

        return rating;
    }

    public String getViewName() {
        return viewName;
    }

    public void setRatedListController(RatedListController ratedListController){
        this.ratedListController = ratedListController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }
}
