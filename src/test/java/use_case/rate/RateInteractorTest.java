package use_case.rate;

import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MockDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class RateInteractorTest {
    MockDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() {
        dataAccessObject = new MockDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user1 = factory.create("Username1", "Password123!");
        dataAccessObject.save(user1);
        User user2 = factory.create("Username2", "Password123!");
        dataAccessObject.save(user2);
        User user3 = factory.create("Username3", "Password123!");
        dataAccessObject.save(user3);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie1 = movieFactory.create();
        movie1.setTitle("Movie1");
        Movie movie2 = movieFactory.create();
        movie2.setTitle("Movie2");
        Movie movie3 = movieFactory.create();
        movie3.setTitle("Movie3");
        Movie movie4 = movieFactory.create();
        movie4.setTitle("Movie4");
        Movie movie5 = movieFactory.create();
        movie5.setTitle("Movie5");

        dataAccessObject.saveToWatchedList("Username1", movie1);
        dataAccessObject.saveToWatchedList("Username1", movie2);
        dataAccessObject.saveToWatchedList("Username1", movie3);

        dataAccessObject.saveToWatchedList("Username2", movie1);
        dataAccessObject.saveToWatchedList("Username2", movie2);
        dataAccessObject.saveToWatchedList("Username2", movie3);

        dataAccessObject.saveToWatchedList("Username3", movie1);
        dataAccessObject.saveToWatchedList("Username3", movie2);
        dataAccessObject.saveToWatchedList("Username3", movie3);
    }

    @Test
    void SuccessTestSingleMatch(){
        RateInputData inputData = new RateInputData("Username1", "Movie1", 5);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                assertEquals("Username1", outputData.getUsername());
                assertEquals("Movie1", outputData.getTitle());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureTestBiggerThan5(){
        RateInputData inputData = new RateInputData("Username1", "Movie2", 10);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Rating must be between 0 and 5 inclusive.", errorMessage);
            }
        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureTestSmallerThan0(){
        RateInputData inputData = new RateInputData("Username1", "Movie3", -10);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Rating must be between 0 and 5 inclusive.", errorMessage);
            }
        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureTestNoMatch(){
        RateInputData inputData = new RateInputData("Username2", "Movie5", 0);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Movie must be in your Watched List before you can rate it.", errorMessage);
            }
        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void SuccessTestMultipleMovies(){
        RateInputData inputData = new RateInputData("Username3", "Movie3", 0);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                assertEquals("Username3", outputData.getUsername());
                assertEquals("Movie3", outputData.getTitle());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
