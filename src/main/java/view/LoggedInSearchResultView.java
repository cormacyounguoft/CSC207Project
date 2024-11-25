package view;

import entity.Movie;
import interface_adapter.add_to_watched_list.AddToWatchedListController;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultController;
import interface_adapter.logged_in_search_result.LoggedInSearchResultState;
import interface_adapter.logged_in_search_result.LoggedInSearchResultViewModel;
import interface_adapter.to_logged_in_view.ToLoggedInViewController;

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

public class LoggedInSearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "logged in search result";

    private final LoggedInSearchResultViewModel loggedInSearchResultViewModel;
    private LoggedInSearchResultController loggedInSearchResultController;
    private AddToWatchlistController addToWatchlistController;
    private AddToWatchedListController addToWatchedListController;
    private ToLoggedInViewController toLoggedInViewController;

    private final JButton addToWatchlist;
    private final JButton addToWatchedList;
    private final JButton cancel;

    private final JPanel movie;
    private final JLabel movieTitle;
    private final JLabel movieReleaseDate;
    private final JLabel movieDescription;
    private final JLabel movieRottenTomatoes;
    private final JLabel movieRuntime;
    private final JLabel movieGenre;
    private final JLabel movieActors;
    private final JLabel movieDirector;
    private final JLabel moviePoster;

    private final JLabel username;

    public LoggedInSearchResultView(LoggedInSearchResultViewModel loggedInSearchResultViewModel) {
        this.loggedInSearchResultViewModel = loggedInSearchResultViewModel;
        this.loggedInSearchResultViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(loggedInSearchResultViewModel.TITLE_LABEL);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        movie = new JPanel();
        movieTitle = new JLabel();
        movieReleaseDate = new JLabel();
        movieDescription = new JLabel();
        movieRottenTomatoes = new JLabel();
        movieRuntime = new JLabel();
        movieGenre = new JLabel();
        movieActors = new JLabel();
        movieDirector = new JLabel();
        moviePoster = new JLabel();

        movie.add(movieTitle);
        movie.add(movieReleaseDate);
        movie.add(movieDescription);
        movie.add(movieRottenTomatoes);
        movie.add(movieRuntime);
        movie.add(movieGenre);
        movie.add(movieActors);
        movie.add(movieDirector);
        movie.add(moviePoster);

        movie.setLayout(new BoxLayout(movie, BoxLayout.Y_AXIS));

        final JPanel buttons = new JPanel();
        addToWatchlist = new JButton(loggedInSearchResultViewModel.ADD_TO_WATCHLIST_BUTTON_LABEL);
        buttons.add(addToWatchlist);
        addToWatchedList = new JButton(loggedInSearchResultViewModel.ADD_TO_WATCHED_LIST_BUTTON_LABEL);
        buttons.add(addToWatchedList);
        cancel = new JButton(loggedInSearchResultViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        addToWatchlist.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        addToWatchlistController.execute(currentState.getUsername(), currentState.getTitle());
                        JOptionPane.showMessageDialog(null, "\"" +
                                currentState.getTitle() + "\" has been added to your watchlist.");
                    }
                }
        );

        addToWatchedList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        addToWatchedListController.execute(currentState.getUsername(), currentState.getTitle());
                        JOptionPane.showMessageDialog(null, "\"" +
                                currentState.getTitle() + "\" has been added to your watched list.");
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final LoggedInSearchResultState currentState = loggedInSearchResultViewModel.getState();
                        toLoggedInViewController.toLoggedInView(currentState.getUsername());
                    }
                }
        );

        JPanel view = new JPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
        view.add(title);
        view.add(username);
        view.add(movie);
        view.add(buttons);

        JScrollPane scroller = new JScrollPane(view);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(1000, 500));
        this.add(scroller);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInSearchResultState state = (LoggedInSearchResultState) evt.getNewValue();
            movieTitle.setText("Title: " + state.getTitle());
            movieReleaseDate.setText("Release Date: " + state.getReleaseDate());
            movieDescription.setText("Description: " + state.getDescription());
            movieRottenTomatoes.setText("Rotten Tomatoes: " + state.getRottenTomatoes());
            movieRuntime.setText("Genres: " + state.getRuntime());
            movieGenre.setText("Actors: " + state.getGenre());
            movieActors.setText("Directors: " + state.getActors());
            movieDirector.setText("Runtime: " + state.getDirector());
            setMoviePoster(state.getPosterLink());
            username.setText("username: " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoggedInSearchResultController(LoggedInSearchResultController loggedInSearchResultController) {
        this.loggedInSearchResultController = loggedInSearchResultController;
    }

    public void setAddToWatchlistController(AddToWatchlistController addToWatchlistController) {
        this.addToWatchlistController = addToWatchlistController;
    }

    public void setAddToWatchedListController(AddToWatchedListController addToWatchedListController) {
        this.addToWatchedListController = addToWatchedListController;
    }

    public void setToLoggedInViewController(ToLoggedInViewController toLoggedInViewController) {
        this.toLoggedInViewController = toLoggedInViewController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setMoviePoster(String posterLink) {
        moviePoster.setIcon(null);
        if (posterLink.equals("Poster not available.")) {
            moviePoster.setText("Poster not available.");
        }
        else {
            try {
                URL url = new URL(posterLink);
                BufferedImage image = ImageIO.read(url);
                moviePoster.setText("");
                moviePoster.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                moviePoster.setText("Poster not available.");
            }
        }
    }
}
