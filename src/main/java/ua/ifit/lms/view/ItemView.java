package ua.ifit.lms.view;

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

}
