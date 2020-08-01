package utils.drivers;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.browser.Browser;

import java.util.HashMap;
import java.util.Map;

public class Chrome extends Browser implements Initialize {

    public void initInLocal() {
//        ChromeDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().version("84.0.4147.30").setup();
        driver = new ChromeDriver(getOptions());
        driver.get("https://www.hepsiburada.com/");
        driver.manage().window().maximize();
    }

    private static ChromeOptions getOptions() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.setCapability("platform", (Platform) null);

        return options;
    }
}
