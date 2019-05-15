package ua.ifit.lms.view;

public class HeaderView {
    public static String getLoggedHeader(String name) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
            return indexSingletonView.getMenu()
                    .replace("<a class=\"nav-link\" href=\"/login\"> Login <span class=\"sr-only\">", "<a class=\"nav-link\" href=\"/logout\"> Log out <span class=\"sr-only\">")
                    .replace("<a class=\"nav-link\" href=\"/reg\"> SingUp </a>", "<a class=\"nav-link\" href=\"/cart\"> " + name + " </a>");
    }

}
