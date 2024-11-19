package view;

import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;

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

/**
 * The View for the Search Result Use Case.
 */
public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "search result";
    private final SearchResultViewModel searchResultViewModel;

    private final JButton toHome;
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

    private SearchResultController searchResultController;

    public SearchResultView(SearchResultViewModel searchResultViewModel) {
        this.searchResultViewModel = searchResultViewModel;
        this.searchResultViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(SearchResultViewModel.TITLE_LABEL);
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
        toHome = new JButton(SearchResultViewModel.TO_HOME_BUTTON_LABEL);
        buttons.add(toHome);

        toHome.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        searchResultController.switchToHomeView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(movie);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SearchResultState state = (SearchResultState) evt.getNewValue();
            movieTitle.setText("Title: " + state.getTitle());
            movieReleaseDate.setText("Release Date: " + state.getReleaseDate());
            movieDescription.setText("Description: " + state.getDescription());
            movieRottenTomatoes.setText("Rotten Tomatoes: " + state.getRottenTomatoes());
            movieRuntime.setText("Genres: " + state.getRuntime());
            movieGenre.setText("Actors: " + state.getGenre());
            movieActors.setText("Directors: " + state.getActors());
            movieDirector.setText("Runtime: " + state.getDirector());
            setMoviePoster(state.getPosterLink());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchResultController(SearchResultController controller) {
        this.searchResultController = controller;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setMoviePoster(String posterLink) {
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
