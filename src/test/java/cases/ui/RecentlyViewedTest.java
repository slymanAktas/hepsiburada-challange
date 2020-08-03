package cases.ui;

import cases.BaseTest;
import models.Buyer;
import models.Product;
import org.junit.*;
import pages.BasketPage;

import java.util.List;

import static models.Buyer.aBuyer;
import static org.hamcrest.MatcherAssert.assertThat;
import static pages.BasketPage.shouldHaveRecentlyViewedProducts;


public class RecentlyViewedTest extends BaseTest {
    @Before
    public void beforeClass() {
        launchBrowser();
    }


    @AfterClass
    public static void tearDownClass() {
        closeBrowser();
    }

    @Test
    public void shouldSeeRecentlyViewedProductOnBasket() {
        Buyer buyer = aBuyer().loginHepsiBurada();
        List<Product> randomThreeProduct = buyer.getRandomProducts(3);
        buyer.visitProductsByOrder(randomThreeProduct);

        BasketPage basketPage = buyer.openBasket();
        assertThat("User couldn't see recently viewed product under basket...", basketPage, shouldHaveRecentlyViewedProducts(randomThreeProduct));
    }
}
