package utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class MyTestNgAppender extends AppenderSkeleton {

    protected void append(LoggingEvent loggingEvent) {
        Reporter.log(layout.format(loggingEvent));
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }
}
