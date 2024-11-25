package use_case.search;

import data_access.MovieAccessObject;
import entity.MovieFactory;
import org.junit.jupiter.api.Test;
import use_case.SearchDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInteractorTest {

    @Test
    void successTest() {
        SearchInputData inputData = new SearchInputData("Frozen");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

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
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(searchDataAccessInterface, successPresenter, new MovieFactory());
        interactor.execute(inputData);

    }

    @Test
    void failTest() {
        SearchInputData inputData = new SearchInputData("123123");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                fail("Use case failure is expected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Movie not found!", error);
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(searchDataAccessInterface, successPresenter, new MovieFactory());
        interactor.execute(inputData);

    }

    @Test
    void successTestEmpty() {
        SearchInputData inputData = new SearchInputData("Shal");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Shal Deyar Na Jaman");
                assertEquals(outputData.getReleaseDate(), "Release date not available.");
                assertEquals(outputData.getDescription(), "Description not available.");
                assertEquals(outputData.getRottenTomatoes(), "Rotten Tomatoes not available.");
                assertEquals(outputData.getRuntime(), "RunTime not available.");
                assertEquals(outputData.getGenre(), "Genre not available.");
                assertEquals(outputData.getActors(), "Actors not available.");
                assertEquals(outputData.getDirector(), "[Dharam Kumar]");
                assertEquals(outputData.getPoster(), "Poster not available.");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(searchDataAccessInterface, successPresenter, new MovieFactory());
        interactor.execute(inputData);
    }

    @Test
    void successTestEmptyDirector() {
        SearchInputData inputData = new SearchInputData("cs");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Ant!cs with Pooja K");
                assertEquals(outputData.getReleaseDate(), "16 May 2017");
                assertEquals(outputData.getDescription(), "Description not available.");
                assertEquals(outputData.getRottenTomatoes(), "Rotten Tomatoes not available.");
                assertEquals(outputData.getRuntime(), "RunTime not available.");
                assertEquals(outputData.getGenre(), "[Short, Comedy, Drama]");
                assertEquals(outputData.getActors(), "[Pooja Kimaya, Kumud Pant, Abdul Zafar]");
                assertEquals(outputData.getDirector(), "Director not available.");
                assertEquals(outputData.getPoster(), "https://m.media-amazon.com/images/M/MV5BYTliODBkZTktYTliYi00MGI1LTkyNDQtNzQ4NDg0OGFmMWE1XkEyXkFqcGc@._V1_SX300.jpg");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(searchDataAccessInterface, successPresenter, new MovieFactory());
        interactor.execute(inputData);
    }
}