import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

class FileLogger implements Logger {
    private Path path;
    private Logger logger = null;

    public FileLogger(Path path) {
        this.path = path;

        try {
            if (Files.exists(path))
                Files.delete(path);
            Files.createFile(path);
        } catch (Exception e) {
            System.out.println("Could not create log file");
            e.printStackTrace();
        }
    }

    public FileLogger(Path path, Logger logger) {
        this(path);
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        if (logger != null) logger.log(message);
        try {
            Files.write(path, message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}