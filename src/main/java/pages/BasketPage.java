package pages;
import browser.Browser;
import models.Product;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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

    public static Matcher<Page> shouldHaveRecentlyViewedProducts(List<Product> products) {
        return new BaseMatcher<Page>() {
            List<String> recentlyViewedProduct;
            List<String> randomThreeProduct;
            String diff;
            @Override
            public boolean matches(final Object item) {
                Page page = (Page) item;
                BasketPage basketPage = (BasketPage) page;
                Browser browser = page.browser();

                recentlyViewedProduct = basketPage.getRecentlyViewedProducts().stream().map(Product::getUrl).collect(Collectors.toList());
                randomThreeProduct = products.stream().map(Product::getUrl).collect(Collectors.toList());
                diff = browser.diffBetweenTwoList(randomThreeProduct, recentlyViewedProduct);

                return diff.equals("");
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Basket page should have reecently viewed products as "+randomThreeProduct+"...");
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText("But "+diff+" products are different...");
            }
        };
    }
}
