package pages;

import utils.browser.Browser;

public class Page extends Browser {
    Browser browser;

    public String url;

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Browser browser(){
        return this.browser;
    }
}
