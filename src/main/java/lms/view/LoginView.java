package lms.view;

import lms.dao.entity.User;
import lms.view.IndexSingletonView;

public class LoginView {

    public String getloginPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getindexHtml();
        String loginForm = indexSingletonView.getLoginForm();
        return indBase.replace("<!--### insert html here ### -->", loginForm);
    }

    public String welcomUserPage(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getindexHtml();
        String loginForm = indexSingletonView.getLoginForm();

        return indBase.replace("<!--### insert html here ### -->", "Hello " + user.getName());
    }
}