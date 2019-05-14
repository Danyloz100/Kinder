package ua.ifit.lms.view;


public class CartView {

    public String getCartPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String cart = indexSingletonView.getCart();
        String menu = indexSingletonView.getMenu();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!--### insert html here ## -->", cart)
                .replace("<!-- Footer -->", footer);
    }
}
