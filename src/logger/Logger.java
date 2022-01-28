package logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Logger {
    private static ILogger logger = null;

    public static void init(ILogger logger) {
        Logger.logger = logger;
    }

    public static void log(String message) {
        if (logger != null)
            logger.log(message);
    }
}

interface ILogger {
    void log(String message);
}

class ConsoleLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.print(message);
    }
}

class FileLogger implements ILogger {
    private Path path;
    private ILogger logger = null;

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

    public FileLogger(Path path, ILogger logger) {
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