package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Good;

public class ItemView {

    public String getItemPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String item= indexSingletonView.getItem();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!--### insert html here ## -->", item)
                .replace("<!-- Footer -->", footer);
    }

    public String addItem(String itemPage, Good good) {
        return itemPage.replace("<!-- price -->", good.getPrice().toString())
                .replace("<!-- name -->", good.getGood_name())
                .replace("<!-- picture -->", "../" + good.getPicture_file_name())
                .replace("<!-- description -->", good.getDescription())
                .replace("<!-- ref -->", "/cart/addtocart/" + good.getIdGood());
    }

}
