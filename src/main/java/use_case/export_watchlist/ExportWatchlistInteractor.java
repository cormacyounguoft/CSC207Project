package use_case.export_watchlist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import entity.Movie;
import entity.MovieList;

/**
 * The interactor for the Export Watchlist Use Case.
 */
public class ExportWatchlistInteractor implements ExportWatchlistInputBoundary {
    private final ExportWatchlistOutputBoundary outputBoundary;
    private final ExportWatchlistDataAccessInterface dataAccess;

    public ExportWatchlistInteractor(ExportWatchlistOutputBoundary outputBoundary, ExportWatchlistDataAccessInterface
            dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void exportWatchlist(ExportWatchlistInputData inputData) {
        try {
            final MovieList watchlist = dataAccess.getWatchlist(inputData.getUserId());
            final List<Movie> movies = watchlist.getMovieList();

            if (movies.isEmpty()) {
                outputBoundary.prepareFailView("The watchlist is empty!");
            }
            else {
                final StringBuilder content = new StringBuilder();
                content.append(inputData.getUserId().toUpperCase()).append("'S TO WATCH LIST\n\n");

                for (Movie movie : movies) {
                    content.append(movie.getTitle())
                            .append(" - ")
                            .append("[")
                            .append(String.join(", ", movie.getGenre()))
                            .append("] - ")
                            .append(movie.getReleaseDate())
                            .append("\n");
                }

                final String filePath = "watchlist_" + inputData.getUserId() + ".txt";
                Files.writeString(Path.of(filePath), content.toString());

                outputBoundary.prepareSuccessView(new ExportWatchlistOutputData(true, filePath, inputData.getUserId()));
            }
        }
        catch (IOException exIoException) {
            outputBoundary.prepareFailView("Unable to export watchlist");
        }
    }
}
