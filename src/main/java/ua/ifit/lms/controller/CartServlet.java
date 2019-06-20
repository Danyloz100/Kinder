package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.dao.repository.OrderRepository;
import ua.ifit.lms.view.CartView;
import ua.ifit.lms.view.HeaderView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сторінка корзини
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart/*"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); //Встановлення кодування ЮТФ-8
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(); // Отримуємо обєкт сесії
        CartView cartView = new CartView();
        User user = (User) session.getAttribute("user"); // Отримуємо обєкт юзера з сесії
        String cartPage = cartView.getCartPage();
        String path = request.getPathInfo();  //Отримуємо шлях, на якому знаходиться юзер
                //Перевіряємо чи юзер залогінився
                if (user != null) {
                    //Перевіряємо чи шлях = buy, якщо так то віднімаємо куплені товари з БД та видаляємо його замовлення

                    if(path != null && path.equals("/buy")) {
                        GoodRepository.substructItems(GoodRepository.getGoodsByUserID(user.getId()));
                        OrderRepository.deleteOrderByUserID(user.getId());
                    }
                    //Перевіряємо, чи користувач перейшов на адресу додавання товару в корзину, якшо так - виконується даний блок коду
                    if(path != null && path.substring(0, path.lastIndexOf("/")).endsWith("addtocart")) {
                            OrderRepository.addOrder(user.getId(), Long.parseLong(request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1)));
                            response.sendRedirect("/cart");
                    }
                    //Якщо юзер вирішив видалити товар з корзини - спрацює цей блок коду
                    if(path != null && path.substring(0, path.lastIndexOf("/")).endsWith("deletefromcart")) {
                        OrderRepository.deleteOrderByUserIDByGoodID(user.getId(), Long.parseLong(request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1)));
                        response.sendRedirect("/cart");
                    }
                    //Виводимо хедер залогіненого юзера
                    out.println(HeaderView.getHeader(user));
                    //Перевірка товару з корзини на наявність, якщо немає в наявності - видаляємо
                    for(Good each: GoodRepository.getGoodsByUserID(user.getId())) {
                        if(each.getCount_of_goods() == 0) OrderRepository.deleteOrderByGoodID(each.getIdGood());
                    }
                    Float totalPrice = 0F; //змінна, яка використовується для знаходження загальної ціни
                    //Формуємо товари на веб сторінці, з даних, які знаходяться в БД та вираховуємо загальну ціну для товарів
                    for(Good each: GoodRepository.getGoodsByUserID(user.getId())) {
                        cartPage = cartView.addItem(cartPage, each);
                        totalPrice += each.getPrice();
                    }
                    //Виведення сформованої веб сторінки з загальною ціною товарів
                    out.println(cartView.addTotalPrice(cartPage, totalPrice.toString()));
                }
                else {
                    // Якщо юзер ще не залогінився, але вирішив додати товар у корзину - запамятовуємо його вибір
                    if(path != null && path.substring(0, path.lastIndexOf("/")).endsWith("addtocart"))
                        session.setAttribute("GoodID", request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1));
                    response.sendRedirect("/login"); //Переправляємо його на вхід
                }
        }

    }

