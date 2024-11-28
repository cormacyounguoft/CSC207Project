package use_case.search;

import data_access.MovieAccessObject;
import entity.MovieFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;
import use_case.SearchDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class SearchInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
    }

    @Test
    void successTest() {
        SearchInputData inputData = new SearchInputData("Movie");

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                assertEquals("title", outputData.getTitle());
                assertEquals("date", outputData.getReleaseDate());
                assertEquals("description", outputData.getDescription());
                assertEquals("0", outputData.getRottenTomatoes());
                assertEquals("0", outputData.getRuntime());
                assertEquals("[genre]", outputData.getGenre());
                assertEquals("[actor]", outputData.getActors());
                assertEquals("[director]", outputData.getDirector());
                assertEquals("url", outputData.getPoster());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(dataAccessObject, successPresenter, new MovieFactory());
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        SearchInputData inputData = new SearchInputData("movie not found");
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

        SearchInputBoundary interactor = new SearchInteractor(dataAccessObject, successPresenter, new MovieFactory());
        interactor.execute(inputData);
    }

    @Test
    void successTestEmpty() {
        SearchInputData inputData = new SearchInputData("blank");

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData outputData) {
                assertEquals("title", outputData.getTitle());
                assertEquals("Release date not available.", outputData.getReleaseDate());
                assertEquals("Description not available.", outputData.getDescription());
                assertEquals("Rotten Tomatoes not available.", outputData.getRottenTomatoes());
                assertEquals("Runtime not available.", outputData.getRuntime());
                assertEquals("Genres not available.", outputData.getGenre());
                assertEquals("Actors not available.", outputData.getActors());
                assertEquals("Directors not available.", outputData.getDirector());
                assertEquals("Poster not available.", outputData.getPoster());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(dataAccessObject, successPresenter, new MovieFactory());
        interactor.execute(inputData);
    }
}