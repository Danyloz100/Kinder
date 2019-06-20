package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.LoginView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сторінка входу
 * */

@WebServlet(name = "StartServlet", urlPatterns = {"/login/*"})
public class StartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Встановлюємо кодування UTF-8
        PrintWriter out = response.getWriter(); // Отримуємо посилання на обєкт для передання браузеру html сторінки
        HttpSession session = request.getSession(); // Отримуємо посилання на обєкт сесії з обєкта запиту
        LoginView loginView = new LoginView();
        String path = request.getPathInfo(); // Отримуємо шлях, на який перейшов юзер
        String loginPage = loginView.getloginPage(); // Отримуємо сторінку входу
        // Перевіряємо, чи юзер перейшов на сторінку виходу з акаунту
        if(path != null && path.substring(path.lastIndexOf("/") + 1).equals("logout"))
            session.setAttribute("user", null); // Встановлюємо значенню атрибуту сесії, який відповідає за юзера значення null
        // Перевіряємо чи юзер ввів дані
        if (request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String email = request.getParameter("email"); // Отримуємо введений користувачем емайл
            String password = request.getParameter("password"); // Отримуємо введений користувачем пароль

            // test repository
            User user = UserRepository.getUserByEmailByPassword(email, password);
            // check if a user successfully logged in
            if (user != null) {
                // Записуємо юзера в атрибут сесії
                session.setAttribute("user", user);
                // Якщо юзер вибрав товар перед входом то додаємо останній вибраний ним товар у корзину
                if(session.getAttribute("GoodID") != null) {
                    String address = "/cart/addtocart/" + session.getAttribute("GoodID"); // Створюємо адресу додавання товару у корзину
                    session.setAttribute("GoodID", null); // Встановлюємо коду продукту значення null, щоб знати, що товар доданий до корзини
                    response.sendRedirect(address); // Направляємо юзера на сторінку додавання товару у корзину
                }
                else
                response.sendRedirect("/"); // Якщо ж юзер не мав вибраного товару до входу, направляємо його на сторінку магазину
            }
            else {
                // Перевіряємо чи юзер не зареєстрований
                if (!UserRepository.isUserRegisterated(email)) {
                    session.setAttribute("LoginInfo", "There's no any user with this email."); // Записуємо повідомлення у атрибут сесії, що юзер не зареєстрований
                } else {
                    session.setAttribute("LoginInfo", "You've typed incorrect password."); // Якщо ж юзер зареєстрований, значить пароль не вірний - записуємо відповідне повідомлення у атрибут сесії
                }
                response.sendRedirect("/login"); // Направляємо юзера на сторінку входу
            }
        } else {
            // Перевіряємо чи є якась інформація про невдалий вхід
            if(session.getAttribute("LoginInfo") != null)
            {
                loginPage = loginView.addInfo(loginPage, (String) session.getAttribute("LoginInfo")); // Додаємо попередньо згенероване повідомлення до сторінки входу
                session.setAttribute("LoginInfo", null); // Встановлюємо атрибуту сесії, який відповідає за повідомлення хибного входу значення null
            }
        }
        out.println(loginPage); // Передаємо сторінку входу браузеру
    }
}
