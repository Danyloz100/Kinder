package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Good;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.GoodRepository;
import ua.ifit.lms.dao.repository.OrderRepository;
import ua.ifit.lms.view.CartView;
import ua.ifit.lms.view.HeaderView;
import ua.ifit.lms.view.IndexSingletonView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "CartServlet", urlPatterns = {"/cart/*"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Встановлення кодування ЮТФ-8
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        CartView cartView = new CartView();
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        User user = (User) session.getAttribute("user");
        String cartPage = cartView.getCartPage();
        //Отримуємо шлях, на якому знаходиться юзер
        String path = request.getPathInfo();


                //Перевіряємо чи дані юзера є в сесії
                if (user != null) {
                    //Перевіряємо чи шлях = buy
                    if(path != null && path.equals("/buy")) {
                        GoodRepository.substructItems(GoodRepository.getGoodsByUserID(user.getId()));
                        OrderRepository.deleteOrderByUserID(user.getId());
                    }
                    //Виводимо хедер залогіненого юзера
                    out.println(HeaderView.getLoggedHeader(user.getName()));
                    int price = 0;
                    //Перевірка товару з корзини на наявність, якщо немає в наявності - видаляємо
                    for(Good each: GoodRepository.getGoodsByUserID(user.getId())) {
                        if(each.getCount_of_goods() == 0) OrderRepository.deleteOrderByGoodID(each.getIdGood());
                    }
                    //Формуємо товари на веб сторінці, з даних, які знаходяться в БД
                    for(Good each: GoodRepository.getGoodsByUserID(user.getId())) {
                        cartPage = cartPage.replace("<!-- item -->", indexSingletonView.getCart_item())
                                .replace("<!-- name -->", each.getGood_name())
                                .replace("<!-- description -->", each.getDescription())
                                .replace("<!-- price -->", each.getPrice().toString())
                                .replace("<!-- picture -->", each.getPicture_file_name())
                                .replace("<!-- address -->", "/deletefromcart/" + each.getIdGood());
                        price += each.getPrice();
                    }
                    //Виведення сформованої веб сторінки з загальною ціною товарів
                    out.println(cartPage.replace("<!-- total -->", String.valueOf(price)));
                }
                else
                    //Якщо даних юзера немає у поточній сесії, переправляємо його на реєстрацію
                    response.sendRedirect("/login");

    }

    }

