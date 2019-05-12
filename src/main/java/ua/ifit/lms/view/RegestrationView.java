package ua.ifit.lms.view;


public class RegestrationView {

    public String getRegestrationPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String regestrationForm = indexSingletonView.getRegestrationForm();
        String menu = indexSingletonView.getMenu();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!-- Menu -->", menu)
                .replace("<!--### insert html here ## -->", regestrationForm)
                .replace("<!-- Footer -->", footer);
    }
}
