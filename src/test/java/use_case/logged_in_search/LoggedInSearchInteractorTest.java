package use_case.logged_in_search;

import data_access.MovieAccessObject;
import org.junit.jupiter.api.Test;
import use_case.SearchDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class LoggedInSearchInteractorTest {

    @Test
    void successTest() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "Frozen");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Frozen");
                assertEquals(outputData.getReleaseDate(), "27 Nov 2013");
                assertEquals(outputData.getDescription(), "Fearless optimist Anna teams up with rugged mountain man Kristoff and his loyal reindeer Sven in an epic journey to find Anna's sister Elsa, whose icy powers have trapped the kingdom of Arendelle in eternal winter.");
                assertEquals(outputData.getRottenTomatoes(), "89");
                assertEquals(outputData.getRuntime(), "102");
                assertEquals(outputData.getGenre(), "[Animation, Adventure, Comedy]");
                assertEquals(outputData.getActors(), "[Kristen Bell, Idina Menzel, Jonathan Groff]");
                assertEquals(outputData.getDirector(), "[Chris Buck, Jennifer Lee]");
                assertEquals(outputData.getPosterLink(), "https://m.media-amazon.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg");
                assertEquals(outputData.getUsername(), "Username");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, searchDataAccessInterface);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "123123");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                fail("Use case failure is expected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Movie not found!", error);
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, searchDataAccessInterface);
        interactor.execute(inputData);
    }

    @Test
    void successTestEmpty() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "Shal");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Shal Deyar Na Jaman");
                assertEquals(outputData.getReleaseDate(), "Release date not available.");
                assertEquals(outputData.getDescription(), "Description not available.");
                assertEquals(outputData.getRottenTomatoes(), "Rotten Tomatoes not available.");
                assertEquals(outputData.getRuntime(), "Runtime not available.");
                assertEquals(outputData.getGenre(), "Genres not available.");
                assertEquals(outputData.getActors(), "Actors not available.");
                assertEquals(outputData.getDirector(), "[Dharam Kumar]");
                assertEquals(outputData.getPosterLink(), "Poster not available.");
                assertEquals(outputData.getUsername(), "Username");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, searchDataAccessInterface);
        interactor.execute(inputData);
    }

    @Test
    void successTestEmptyDirector() {
        LoggedInSearchInputData inputData = new LoggedInSearchInputData("Username", "cs");
        SearchDataAccessInterface searchDataAccessInterface = new MovieAccessObject();

        LoggedInSearchOutputBoundary successPresenter = new LoggedInSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoggedInSearchOutputData outputData) {
                assertEquals(outputData.getTitle(), "Ant!cs with Pooja K");
                assertEquals(outputData.getReleaseDate(), "16 May 2017");
                assertEquals(outputData.getDescription(), "Description not available.");
                assertEquals(outputData.getRottenTomatoes(), "Rotten Tomatoes not available.");
                assertEquals(outputData.getRuntime(), "Runtime not available.");
                assertEquals(outputData.getGenre(), "[Short, Comedy, Drama]");
                assertEquals(outputData.getActors(), "[Pooja Kimaya, Kumud Pant, Abdul Zafar]");
                assertEquals(outputData.getDirector(), "Directors not available.");
                assertEquals(outputData.getPosterLink(), "https://m.media-amazon.com/images/M/MV5BYTliODBkZTktYTliYi00MGI1LTkyNDQtNzQ4NDg0OGFmMWE1XkEyXkFqcGc@._V1_SX300.jpg");
                assertEquals(outputData.getUsername(), "Username");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoggedInSearchInputBoundary interactor = new LoggedInSearchInteractor(successPresenter, searchDataAccessInterface);
        interactor.execute(inputData);
    }
}