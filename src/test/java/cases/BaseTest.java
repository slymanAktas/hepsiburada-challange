package cases;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import utils.browser.Browser;


public class BaseTest extends Browser {
    @Rule
    public TestRule listen = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot("/"+description.getMethodName()+"");
        }

    };

    protected BaseTest() {
    }
}
