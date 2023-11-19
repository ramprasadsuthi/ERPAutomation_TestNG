package commonMethods;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class GetLatestFile {

    public String getLatestFile() {
        String directoryPath = "C:/Users/rampr/Downloads"; // Replace with the actual path to your directory
        String latestFileName = null;

        try {
        	latestFileName = getLatestFileName(directoryPath);
            System.out.println("Latest file: " + latestFileName);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestFileName;
    }

    public static String getLatestFileName(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);

        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Provided path is not a directory.");
        }

        // Get the latest file based on last modified time
        return Files.walk(dir)
                .filter(Files::isRegularFile)
                .max(Comparator.comparingLong(file -> {
                    try {
                        return Files.getLastModifiedTime(file).toMillis();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }))
                .map(Path::getFileName)
                .map(Path::toString)
                .orElse(null);
    }
}


