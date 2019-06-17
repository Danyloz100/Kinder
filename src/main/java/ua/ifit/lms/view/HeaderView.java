package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.User;

public class HeaderView {
    public static String getHeader(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String menu = indexSingletonView.getMenu();
        if(user != null)
            return menu
                    .replace("<!-- first link -->", "/login/logout")
                    .replace("<!-- second link -->", "/cart")
                    .replace("<!-- first content -->", "Log out")
                    .replace("<!-- second content -->", user.getName());
        else
            return menu
                    .replace("<!-- first link -->", "/login")
                    .replace("<!-- second link -->", "/reg")
                    .replace("<!-- first content -->", "Login")
                    .replace("<!-- second content -->", "Sign up");
    }

}
