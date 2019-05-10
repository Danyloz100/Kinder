package ua.ifit.lms.view;

public class ShopView {
    public String getShopPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String shop = indexSingletonView.getShop();
        String menu = indexSingletonView.getMenu();
        return indBase
                .replace("<!--### insert html here ### -->", menu)
                .replace("<!--### insert html here ### -->", shop);

    }
}
