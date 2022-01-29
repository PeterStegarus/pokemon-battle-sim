package logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLogger implements ILogger {
    private Path path;
    private ILogger logger = null;

    public FileLogger(String path) {
        this.path = Path.of("log/" + path);

        try {
            if (!Files.exists(Path.of("log")))
                Files.createDirectory(Path.of("log"));
            if (Files.exists(this.path))
                Files.delete(this.path);
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
            Files.write(path, message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
