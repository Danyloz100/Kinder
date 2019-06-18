package ua.ifit.lms.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexSingletonView {

    private String path;
    private String indexHtml;
    private String loginForm;
    private String registrationForm;
    private String menu;
    private String shop;
    private String footer;
    private String item;
    private String item_element;
    private String cart;
    private String cart_item;
    private String catalog_item;




    private static IndexSingletonView ourInstance = new IndexSingletonView();

    public static IndexSingletonView getInstance() {
        return ourInstance;
    }



    private IndexSingletonView() {
    }

    public String getCatalog_item() {
        return catalog_item;
    }

    public void setCatalog_item(String catalog_item) {
        this.catalog_item = catalog_item;
    }

    public String getCart_item() {
        return cart_item;
    }

    public void setCart_item(String cart_item) {
        this.cart_item = cart_item;
    }

    public void setPath(String path) {
        this.path = path;
        this.indexHtml = getPartialHtml("index");
        this.loginForm = getPartialHtml("login-form");
        this.menu = getPartialHtml("menu");
        this.shop = getPartialHtml("shop");
        this.footer = getPartialHtml("footer");
        this.registrationForm = getPartialHtml("registration");
        this.item = getPartialHtml("item");
        this.item_element = getPartialHtml("item_element");
        this.cart = getPartialHtml("cart");
        this.cart_item = getPartialHtml("cart_item");
        this.catalog_item = getPartialHtml("catalog_item");
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getItem_element() {
        return item_element;
    }

    public void setItem_element(String item_element) {
        this.item_element = item_element;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setRegistrationForm(String registrationForm) {
        this.registrationForm = registrationForm;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getIndexHtml() {
        return indexHtml;
    }

    public String getLoginForm() {
        return loginForm;
    }

    public String getMenu() {
        return menu;
    }

    public String getShop() {
        return shop;
    }

    public String getFooter() {
        return footer;
    }

    public String getRegistrationForm() {
        return registrationForm;
    }

    public String getItem() {
        return item;
    }

    public String getCart() {
        return cart;
    }

    private String getPartialHtml(String filename){
        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return strb.toString();
    }
}
