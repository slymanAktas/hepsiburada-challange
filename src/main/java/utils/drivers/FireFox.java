package utils.drivers;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import utils.browser.Browser;

public class FireFox extends Browser implements Initialize {

    public  void initInLocal() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver(getOptions());
        driver.get("https://www.hepsiburada.com/");
        driver.manage().window().maximize();
    }

    private static FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(FirefoxDriver.MARIONETTE, true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.PLATFORM_NAME, (Platform) null);

        return options;
    }
}
