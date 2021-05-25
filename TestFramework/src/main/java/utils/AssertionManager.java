package utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class AssertionManager {

    private static final Logger LOGGER = Logger.getLogger(AssertionManager.class);

    public static void assertEqualsAndLog(Object actualResult, Object expectedResult) {
        LOGGER.info("Expected result (" + expectedResult.toString() + ") equals to actual result (" + actualResult.toString() + ")");
        Assert.assertEquals(actualResult, expectedResult);
    }

    public static void assertTrueAndLog(boolean expression) {
        LOGGER.info("Expected result (" + true + "), actual result (" + expression + ")");
        Assert.assertTrue(expression);
    }
}
