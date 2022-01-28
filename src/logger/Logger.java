package logger;

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