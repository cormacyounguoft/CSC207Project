package use_case.search;

import data_access.MovieAccessObject;
import entity.Movie;
import entity.MovieFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInteractorTest {

    @Test
    void successTest() {
        SearchInputData inputData = new SearchInputData("Frozen");
        LoggedOutSearchDataAccessInterface loggedOutSearchDataAccessInterface = new MovieAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                Movie movie = new Movie();
                movie.setTitle("Frozen");
                movie.setReleaseDate("27 Nov 2013");
                movie.setDescription("Fearless optimist Anna teams up with rugged mountain man Kristoff and his loyal reindeer Sven in an epic journey to find Anna's sister Elsa, whose icy powers have trapped the kingdom of Arendelle in eternal winter.");
                movie.setRottenTomatoes(89);
                movie.setRuntime(102);
                movie.setGenre(new ArrayList<>(Arrays.asList("Animation","Adventure","Comedy")));
                movie.setActors(new ArrayList<>(Arrays.asList("Kristen Bell","Idina Menzel","Jonathan Groff")));
                movie.setDirector(new ArrayList<>(Arrays.asList("Chris Buck","Jennifer Lee")));
                movie.setPosterLink("https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg");

                assertEquals(outputData.getMovie(), movie);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(loggedOutSearchDataAccessInterface, successPresenter, new MovieFactory());
        interactor.execute(inputData);

    }
}