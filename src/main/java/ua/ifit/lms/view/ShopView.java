package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.User;

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