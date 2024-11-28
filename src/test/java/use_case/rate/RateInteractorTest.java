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
        User user = factory.create("Username", "Password123!");
        dataAccessObject.save(user);

        MovieFactory movieFactory = new MovieFactory();
        Movie movie = movieFactory.create();
        movie.setTitle("Movie");
        dataAccessObject.saveToWatchedList("Username", movie);
    }

    @Test
    void SuccessTest(){
        RateInputData inputData = new RateInputData("Username", "Movie", 5);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                assertEquals("Username", outputData.getUsername());
                assertEquals("Movie", outputData.getTitle());
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
        RateInputData inputData = new RateInputData("Username", "Movie", 10);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(errorMessage, "Rating must be between 0 and 5 inclusive.");
            }

        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureTestSmallerThan0(){
        RateInputData inputData = new RateInputData("Username", "Movie", -10);

        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(errorMessage, "Rating must be between 0 and 5 inclusive.");
            }

        };

        RateInputBoundary interactor = new RateInteractor(dataAccessObject, presenter);
        interactor.execute(inputData);
    }
}
