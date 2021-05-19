package utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class AssertionManager {

    private static final Logger LOGGER = Logger.getLogger(AssertionManager.class);

    public static void assertEqualsAndLog(Object actualResult, Object expectedResult) {
        LOGGER.info("Expected result (" + expectedResult.toString() + ") equals to actual result (" + actualResult.toString() + ")");
        Assert.assertEquals(actualResult, expectedResult);
    }

    public static void assertFalseAndLog(boolean expression) {
        LOGGER.info("Expected result (" + false + "), actual result (" + expression + ")");
        Assert.assertFalse(expression);
    }
}
