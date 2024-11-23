package view;

import interface_adapter.search_result.SearchResultController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.to_home_view.ToHomeViewController;

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
import java.util.Objects;

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
    private ToHomeViewController toHomeViewController;

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
                        toHomeViewController.toHomeView();
                    }
                }
        );
        JPanel view = new JPanel();
        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
        view.add(title);
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
            final SearchResultState state = (SearchResultState) evt.getNewValue();


            movieTitle.setText("Title: " + state.getTitle());
            movieReleaseDate.setText("Release Date: " + state.getReleaseDate());
            movieDescription.setText("Description: " + state.getDescription());
            movieRottenTomatoes.setText("Rotten Tomatoes: " + state.getRottenTomatoes());
            movieRuntime.setText("Runtime: " + state.getRuntime());
            movieGenre.setText("Genres: " + state.getGenre());
            movieActors.setText("Actors: " + state.getActors());
            movieDirector.setText("Directors: " + state.getDirector());
            setMoviePoster(state.getPoster());
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


    public void setMoviePoster(String poster) {
        moviePoster.setIcon(null);
        if (Objects.equals(poster, "Poster not available.")) {
            moviePoster.setText("Poster not available.");
        }
        else {
            try {
                URL url = new URL(poster);
                BufferedImage image = ImageIO.read(url);
                moviePoster.setText("");
                moviePoster.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                System.out.println("Poster not available");
            }
        }
    }

    public void setToHomeViewController(ToHomeViewController toHomeViewController) {
        this.toHomeViewController = toHomeViewController;
    }
}
