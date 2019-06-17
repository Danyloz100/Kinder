package ua.ifit.lms.view;


import ua.ifit.lms.dao.entity.Good;

public class CartView {

    public String getCartPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String cart = indexSingletonView.getCart();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!--### insert html here ## -->", cart)
                .replace("<!-- Footer -->", footer);
    }

    public String addItem(String cartPage, Good good) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        return cartPage.replace("<!-- item -->", indexSingletonView.getCart_item())
                .replace("<!-- name -->", good.getGood_name())
                .replace("<!-- description -->", good.getDescription())
                .replace("<!-- price -->", good.getPrice().toString())
                .replace("<!-- picture -->", good.getPicture_file_name())
                .replace("<!-- address -->", "/cart/deletefromcart/" + good.getIdGood());
    }

    public String addTotalPrice(String cartPage, String totalPrice) {
        return cartPage.replace("<!-- total -->", totalPrice);
    }
}
