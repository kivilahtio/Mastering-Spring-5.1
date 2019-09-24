package test.solita.kivilahtio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestLogger extends TestWatcher {
    private Logger logger = LogManager.getLogger(TestLogger.class);

    public int testNumber = 0;

    @Override
    protected void failed(Throwable e, Description description) {
        incrementTestContext("Er");
        logger.info("Fail: "+description.toString() + e);
    }

    @Override
    protected void succeeded(Description description) {
        incrementTestContext("OK");
        logger.info(description);
    }

    private void incrementTestContext(String status) {
        ThreadContext.put("testNumber", Integer.toString(++testNumber));
        ThreadContext.put("testStatus", status);
    }
}
