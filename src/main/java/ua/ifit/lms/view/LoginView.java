package ua.ifit.lms.view;

public class LoginView {

    public String getloginPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String loginForm = indexSingletonView.getLoginForm();
        String menu = indexSingletonView.getMenu();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!-- Menu -->", menu)
                .replace("<!--### insert html here ## -->", loginForm)
                .replace("<!-- Footer -->", footer);
    }

    public String addInfo(String loginPage, String message) {
        return loginPage.replace("<!-- info -->", "<span class=\"label-input100 text-danger\">" + message + "</span>");
    }
}
