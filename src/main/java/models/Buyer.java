package models;


import org.openqa.selenium.By;
import pages.BasketPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestException;
import browser.Browser;

import java.util.List;
import java.util.stream.Collectors;

public class Buyer {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Browser browser;

    private By dodProducts = By.cssSelector("div.hb_dod_product a");


    Buyer(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static Buyer aBuyer() {
        return new Buyer("Süleyman", "Aktaş", "suleyman.aktas@gmail.com", "Passw0rd");
    }

    public Buyer loginHepsiBurada() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login();
        this.browser = homePage;
        browser.assertIsPageRedirectedSuccessfuly(homePage);
        return this;
    }

    public List<Product> getRandomProductsFromDOD(int count) {
        List<String> dodProductUrls = browser.findElements(dodProducts).stream().map(each -> each.getAttribute("href")).collect(Collectors.toList());
        if (dodProductUrls.size() < count) {
            throw new TestException("Desired product count can not be more then total product under Deal Of Day...");
        }
        List<Product> randomProducts = dodProductUrls.subList(0, count).stream().map(each -> new Product(each)).collect(Collectors.toList());
        return randomProducts;
    }

    public void visitProductsByOrder(List<Product> products) {
        for (Product each : products) {
            browser.goTo(each);
        }
    }

    public BasketPage openBasket() {
        BasketPage basketPage = new BasketPage();
        browser.goTo(basketPage);
        return basketPage;
    }
}
