package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.User;

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

    public String welcomUserPage(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String loginForm = indexSingletonView.getLoginForm();
        return indBase.replace("<!--### insert html here ### -->", "Hello " + user.getName());
    }
}
