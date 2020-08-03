package drivers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.TestException;

import java.net.MalformedURLException;
import java.net.URL;

import static config.Config.ZALENIUM_GRID_HUB_URL;


public class RemoteDrive {

    private RemoteDrive() {
    }

    public static WebDriver create(MutableCapabilities options) {
        WebDriver webDriver;
        try {
            webDriver = new RemoteWebDriver(new URL(ZALENIUM_GRID_HUB_URL), options);
            webDriver.manage().window().setSize(new Dimension(1216, 1024));
            ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException mue) {
            throw new TestException(mue);
        }
        return webDriver;
    }
}
