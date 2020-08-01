package pages;

import models.Product;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class BasketPage extends Page {
    private static final String PAGE_URL = "https://www.hepsiburada.com/ayagina-gelsin/sepetim";
    private By recentlyViewedProducts = By.xpath("//div[text()='Son gezdiğiniz ürünler']//following::div[@class='product-detail']/a");

    public BasketPage(){
        this.url = PAGE_URL;
        this.browser = this;
    }

    public List<Product> getRecentlyViewedProducts() {

        List<String> recentlyViewedProductUrls = browser.findElements(recentlyViewedProducts).stream().map(each -> each.getAttribute("href")).collect(Collectors.toList());
        return recentlyViewedProductUrls.stream().map(each -> new Product(each)).collect(Collectors.toList());
    }
}
