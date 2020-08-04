package browser;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.ScreenshotException;
import io.qameta.allure.Attachment;

import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.Page;
import config.Config;
import drivers.Chrome;
import drivers.FireFox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static config.Config.ISREMOTE;
import static config.Config.TIMEOUT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.OutputType.BYTES;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;
import static utils.FileUtils.writeFile;


public abstract class Browser extends BrowserUtils {

    Page page;

    protected static void launchBrowser() {
        if (ISREMOTE) {
            if (Config.DEFAULT_BROWSER_NAME.equalsIgnoreCase("firefox")) {
                new FireFox().initInGrid();
            } else if (Config.DEFAULT_BROWSER_NAME.equalsIgnoreCase("chrome")) {
                new Chrome().initInGrid();
            }
        } else {
            if (Config.DEFAULT_BROWSER_NAME.equalsIgnoreCase("firefox")) {
                new FireFox().initInLocal();
            } else if (Config.DEFAULT_BROWSER_NAME.equalsIgnoreCase("chrome")) {
                new Chrome().initInLocal();
            }
        }
    }


    protected static void closeBrowser() {
        driver.close();
    }

    public <P extends Page> Page goTo(P page) {
        driver.get(page.url);
        waitForDialog();
        if (!(page instanceof LoginPage)) {
            waitForUrlHasNavigatedTo(TIMEOUT, page.url);
        }
        return new Page();
    }


    public void clickToBy(By by) {
        clickTo(visibilityWait(TIMEOUT, by));
    }

    private void clickTo(WebElement element) {
        waitForEnableOf(TIMEOUT, element);
        waitForClickableOf(TIMEOUT, element);
        highlightElement(element);
        element.click();
        waitForDialog();
    }

    public void type(By by, String text, boolean clear) {
        WebElement element = visibilityWait(TIMEOUT, by);
        waitForEnableOf(TIMEOUT, element);
        waitForClickableOf(TIMEOUT, element);
        highlightElement(element);
        scrollToElement(element);
        if (clear) {
            element.clear();
            waitForDialog();
        }

        element.sendKeys(text);
        waitForDialog();
    }

    public WebElement findElement(By by, WebElement element) {
        List<WebElement> elements;
        if (element != null) {
            elements = element.findElements(by);
        } else {
            elements = driver.findElements(by);
        }
        return elements.isEmpty() ? null : elements.get(0);
    }

    public WebElement findElement(By by) {
        return findElement(by, null);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    private void waitForDialog() {
        int i = 0;
        while (i < 15) {
            waitForAjax();
            i++;
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", element);
    }

    private void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.background = 'yellow';", element);
    }

    private String currentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (TimeoutException toe) {
            System.out.println("Timeout when trying to get currentUrl!");
            return null;
        } catch (WebDriverException wde) {
            System.out.println("Exception occured while trying to get currentUrl, " + wde);
            return null;
        }
    }

    public void waitForUrlHasNavigatedTo(int seconds, String urlToBe) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(urlToBe(urlToBe));
        waitForDialog();
    }

    public void assertIsPageRedirectedSuccessfuly(Page page) {
        assertThat("Browser couldn't be redirected relative page", currentURL(), is(equalTo(page.url)));
    }

    @Attachment(value = "Fail screenshot", type = "image/png")
    public static byte[] takeScreenshot(String scrFilename) {
        byte[] byteArray = new byte[0];
        try {
            byteArray = ((TakesScreenshot) driver).getScreenshotAs(BYTES);
            writeFile(scrFilename, byteArray);
        } catch (ScreenshotException sse) {
            System.out.println("Taking screenshot has been failed, " + sse);
        }
        return byteArray;
    }

    public String diffBetweenTwoList(java.util.List expectedValues, java.util.List actualValues) {
        java.util.List<String> diff = new ArrayList<>();
        for (String temp : (java.util.List<String>) expectedValues) {
            if (!actualValues.contains(temp)) {
                diff.add(temp);
            }
        }
        return diff.stream().collect(Collectors.joining("\n"));
    }
}
