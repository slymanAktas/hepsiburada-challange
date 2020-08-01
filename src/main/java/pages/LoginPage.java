package pages;

import org.openqa.selenium.By;

public class LoginPage extends Page {
    private static final String PAGE_URL = "https://giris.hepsiburada.com";
    private By emailTextBox = By.cssSelector("input#txtUserName");
    private By passwordTextBox = By.cssSelector("input#txtPassword");
    private By loginButton = By.id("btnLogin");

    public LoginPage() {
        this.url = PAGE_URL;
        this.browser = this;
    }

    public HomePage login() {
        browser.goTo(this);
        browser.type(emailTextBox, "suleyman.aktas@gmail.com", false);
        browser.type(passwordTextBox, "Passw0rd", false);
        browser.clickToBy(loginButton);

        HomePage homePage = new HomePage();
        browser.waitForUrlHasNavigatedTo(15, homePage.url);

        return homePage;
    }

}
