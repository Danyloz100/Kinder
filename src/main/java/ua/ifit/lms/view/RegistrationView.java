package ua.ifit.lms.view;


public class RegistrationView {

    public String getRegestrationPage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String registrationForm = indexSingletonView.getRegistrationForm();
        String menu = indexSingletonView.getMenu();
        String footer = indexSingletonView.getFooter();
        return indBase
                .replace("<!-- Menu -->", menu)
                .replace("<!--### insert html here ## -->", registrationForm)
                .replace("<!-- Footer -->", footer);
    }

    public String addInfo(String registrationPage, String message) {
        return registrationPage.replace("<!-- info -->", "<span class=\"label-input100\" style=\"color: red;\">" + message + "</span>");
    }
}
