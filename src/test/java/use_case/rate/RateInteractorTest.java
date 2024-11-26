package use_case.rate;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class RateInteractorTest {

    @Test
    void SuccessTest(){
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "Password1!");
        RateInputData inputData = new RateInputData("Paul", "Frozen", 5);
        userRepository.save(user);
        Movie movie = new Movie();
        movie.setTitle("Frozen");
        userRepository.saveToWatchedList("Paul", movie);
        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                assertEquals(outputData.getUsername(), "Paul");
                assertEquals(outputData.getTitle(), "Frozen");
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoggedInView(RateOutputData outputData) {

            }

        };
        RateInputBoundary interactor = new RateInteractor(userRepository, presenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureTest(){
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "Password1!");
        RateInputData inputData = new RateInputData("Paul", "Frozen", 10);
        userRepository.save(user);
        Movie movie = new Movie();
        movie.setTitle("Frozen");
        userRepository.saveToWatchedList("Paul", movie);
        RateOutputBoundary presenter = new RateOutputBoundary() {
            @Override
            public void prepareSuccessView(RateOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Rating must be between 0 and 5 inclusive.", errorMessage);
            }

            @Override
            public void switchToLoggedInView(RateOutputData outputData) {

            }

        };
        RateInputBoundary interactor = new RateInteractor(userRepository, presenter);
        interactor.execute(inputData);
    }
}
