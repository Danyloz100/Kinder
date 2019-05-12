package ua.ifit.lms.view;

public class ShopView {
    public String getShopPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String shop = indexSingletonView.getShop();
        String menu = indexSingletonView.getMenu();
        String footer = indexSingletonView.getFooter();
        return indBase

                .replace("<!--### insert html here ## -->", shop)
                .replace("<!-- Footer -->", footer);


    }
}