package models;

import pages.Page;

public class Product extends Page {

    public Product(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
