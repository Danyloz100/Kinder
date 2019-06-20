package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Catalog;
import ua.ifit.lms.dao.entity.Good;

public class ShopView {
    public String getShopPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String shop = indexSingletonView.getShop();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!--### insert html here ## -->", shop)
                .replace("<!-- Footer -->", footer);
    }

    public String addCategory(String shopPage, Catalog catalog, String check) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        return shopPage
                .replace("<!-- element -->", indexSingletonView.getCatalog_item())
                .replace("<!-- name -->", catalog.getDescription())
                .replace("<!-- id -->", catalog.getId().toString())
                .replace("<!-- check -->", check);
    }

    public String addItem(String shopPage, Good good, String address) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        return shopPage
                .replace("<!-- item -->", indexSingletonView.getItem_element()
                        .replace("<!-- price -->", good.getPrice().toString())
                        .replace("<!-- name -->",good.getGood_name())
                        .replace("<!-- picture -->", good.getPicture_file_name())
                        .replace("<!-- description -->", good.getDescription())
                        .replace("<!-- address -->", address));
    }

    public String addSearchValue(String shopPage, String value) {
        return shopPage
                .replace("><!-- value -->", "value=\"" + value + "\">");
    }

    public String addGroupNameForCatalogs(String shopPage, String catalogType) {
        return shopPage
                .replace("<!-- element -->", "<div style=\"text-align: center; padding: 5px 0; background-color: white;\">" + catalogType + "</div>\n <!-- element -->");
    }
}