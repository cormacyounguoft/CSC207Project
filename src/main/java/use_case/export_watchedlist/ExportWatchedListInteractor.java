package use_case.export_watchedlist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import entity.Movie;
import entity.MovieList;

/**
 * The interactor for the Export Watched List Use Case.
 */
public class ExportWatchedListInteractor implements ExportWatchedListInputBoundary {
    private final ExportWatchedListOutputBoundary outputBoundary;
    private final ExportWatchedListDataAccessInterface dataAccess;

    public ExportWatchedListInteractor(ExportWatchedListOutputBoundary outputBoundary,
                                       ExportWatchedListDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void exportWatchedList(ExportWatchedListInputData inputData) {
        try {
            final MovieList watchedList = dataAccess.getWatchedList(inputData.getUserId());
            final List<Movie> movies = watchedList.getMovieList();

            if (movies.isEmpty()) {
                outputBoundary.prepareFailView("The watched list is empty!");
                return;
            }

            final StringBuilder content = new StringBuilder();

            content.append(inputData.getUserId().toUpperCase()).append("'S WATCHED LIST\n\n");

            for (Movie movie : movies) {
                content.append(movie.getTitle())
                        .append(" - ")
                        .append("[")
                        .append(String.join(", ", movie.getGenre()))
                        .append("] - ")
                        .append(movie.getReleaseDate())
                        .append("\n");
            }

            final String filePath = "watchedlist_" + inputData.getUserId() + ".txt";
            Files.writeString(Path.of(filePath), content.toString());

            outputBoundary.prepareSuccessView(new ExportWatchedListOutputData(true, filePath, inputData.getUserId()));
        } catch (IOException exIoException) {
            outputBoundary.prepareFailView("Unable to export watched list");
        }
    }
}
