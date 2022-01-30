package logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLogger implements ILogger {
    private final Path path;
    private ILogger logger = null;

    public FileLogger(String path) {
        this.path = Path.of("logs/" + path);

        try {
            if (!Files.exists(Path.of("logs"))) // if logs directory doesn't exist, create it
                Files.createDirectory(Path.of("logs"));
            // if log file already exists, delete it
            if (Files.exists(this.path))
                Files.delete(this.path);
            // create a new empty file where the logger will append logs to
            Files.createFile(this.path);
        } catch (Exception e) {
            System.out.println("Could not create log file");
            e.printStackTrace();
        }
    }

    public FileLogger(String path, ILogger logger) {
        this(path);
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        if (logger != null) logger.log(message);
        try {
            Files.writeString(path, message, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
