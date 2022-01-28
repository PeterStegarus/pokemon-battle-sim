package logger;

public class ConsoleLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.print(message);
    }
}
