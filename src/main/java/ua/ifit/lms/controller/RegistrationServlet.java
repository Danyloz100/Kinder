package ua.ifit.lms.controller;

import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.RegistrationView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/reg"})
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Встановлюємо кодування UTF-8
        PrintWriter out = response.getWriter(); // Отримуємо посилання на обєкт для передання браузеру html сторінки
        HttpSession session = request.getSession(); // Отримуємо посилання на обєкт сесії з обєкта запиту
        RegistrationView registrationView = new RegistrationView(); // Отримуємо обєкт типу RegistrationView
        // Перевіряємо, чи є якесь повідомлення про невдалу реєстрацію
        if (session.getAttribute("info") != null) {
            out.println(registrationView.addInfo(registrationView.getRegestrationPage(), session.getAttribute("info").toString())); // Виводимо сторінку реєстрації та повідомлення про невдалу реєстрацію
            session.setAttribute("info", null); // Анульовуємо повідомлення в сесії
        } else
            out.println(registrationView.getRegestrationPage()); // Якщо ж повідомлень немає - виводимо сторінку реєстрації
        // Перевіряємо чи введені користувачем поля(інпути) не пусті
        if (request.getParameter("name") != null &&
            request.getParameter("email") != null &&
            request.getParameter("password") != null &&
            request.getParameter("repeat-password") != null) {
                String name = request.getParameter("name"); // Отримуємо нікнейм або ПІБ користувача введений в інпут
                String email = request.getParameter("email"); // Отримуємо електронну пошту користувача введений в інпут
                String password = request.getParameter("password"); // Отримуємо пароль користувача введений в інпут
                String repeatPassword = request.getParameter("repeat-password"); // Отримуємо повторний пароль користувача введений в інпут
                String check = request.getParameter("remember-me"); // Отримуємо стан чек-інпуту, якщо він натиснутий отримаємо on, в іншому випадку отримаємо null
                // Перевіряємо чи юзер не погодився з правилами користувача
                if(check == null) {
                    session.setAttribute("info", "Please, agree with user terms."); // Якщо не погодився - записуємо це у атрибут сесії
                    response.sendRedirect("/reg"); // Перенаправляємо його на сторінку реєстрації
                } else
                    // Перевіряємо чи юзер не зареєстрований
                    if (!UserRepository.isUserRegisterated(email)) {
                        // Перевіряємо чи обидва введені паролі є ідентичними
                        if (!password.equals(repeatPassword)) {
                            session.setAttribute("info", "Both passwords are not identical."); // Якщо вони не ідентичні - записуємо це у атрибут сесії
                            response.sendRedirect("/reg"); // Перенаправляємо його на сторінку реєстрації
                        }
                        // Якщо ж паролі ідентичні виконується додавання користувача до БД
                        else {
                            UserRepository.setUserByEmailByPassword(name, email, password); // Додавання користувача в БД
                            response.sendRedirect("/login"); // Перенаправляємо його на сторінку входу
                        }
                    } else {
                        session.setAttribute("info", "User with this email is already registered."); // Якщо ж такий юзер вже зареєстрований - записуємо це у атрибут сесії
                        response.sendRedirect("/reg"); // Перенаправляємо його на сторінку реєстрації
                    }
        }
    }
}
