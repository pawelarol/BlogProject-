package web.Interfaces;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// класс через который будут проходить все данные от клиента и
// отправлять на сервлеты - этот класс-развилка

public abstract class ServletManager extends HttpServlet {

     public abstract void doPost(HttpServletRequest req, HttpServletResponse resp);
     public abstract void doGet(HttpServletRequest req, HttpServletResponse resp);

}
