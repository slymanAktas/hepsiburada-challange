package pages;


public class HomePage extends Page {
    private static final String PAGE_URL = "https://www.hepsiburada.com/";

    public HomePage() {
        this.url = PAGE_URL;
        this.browser = this;
    }

}
