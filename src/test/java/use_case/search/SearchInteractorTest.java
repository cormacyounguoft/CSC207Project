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
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Frozen");
                assertEquals(outputData.getReleaseDate(), "27 Nov 2013");
                assertEquals(outputData.getDescription(), "Fearless optimist Anna teams up with rugged mountain man Kristoff and his loyal reindeer Sven in an epic journey to find Anna's sister Elsa, whose icy powers have trapped the kingdom of Arendelle in eternal winter.");
                assertEquals(outputData.getRottenTomatoes(), "89");
                assertEquals(outputData.getRuntime(), "102");
                assertEquals(outputData.getGenre(), "[Animation, Adventure, Comedy]");
                assertEquals(outputData.getActors(), "[Kristen Bell, Idina Menzel, Jonathan Groff]");
                assertEquals(outputData.getDirector(), "[Chris Buck, Jennifer Lee]");
                assertEquals(outputData.getPoster(), "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg");

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(searchDataAccessInterface, successPresenter);
        interactor.execute(inputData);

    }
}