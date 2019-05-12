package ua.ifit.lms.view;

public class LoginView {

    public String getloginPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String loginForm = indexSingletonView.getLoginForm();
        String menu = indexSingletonView.getMenu();
        return indBase
                .replace("<!-- Menu -->", menu)
                .replace("<!--### insert html here ## -->", loginForm);
    }

}
