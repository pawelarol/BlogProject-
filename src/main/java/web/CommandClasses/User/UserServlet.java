package web.CommandClasses.User;

import service.dao.DictionaryDaoImpl;
import service.domian.BlogUserRequest;
import web.Interfaces.ServletManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserServlet extends ServletManager {

    private DictionaryDaoImpl dao;

    public UserServlet(){
        dao = new DictionaryDaoImpl();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        BlogUserRequest userRequest = new BlogUserRequest();
         userRequest.setUserName(req.getParameter("userName"));

        try {

            BlogUserRequest baseAnswer = dao.getUser(userRequest);
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>User Details</h1>");
            out.println("<p>User Name: " + baseAnswer.getUserName() + "</p>");
            out.println("<p>User Email: " + baseAnswer.getUserMail() + "</p>");
            out.println("<p>User Password: " + baseAnswer.getUserPassword() + "</p>");
            out.println("<p>First and Last Name: " + baseAnswer.getFirstLastName() + "</p>");
            out.println("<p>About Yourself: " + baseAnswer.getAboutYourself() + "</p>");
            out.println("<p>Date of Register: " + baseAnswer.getDateOfRegister() + "</p>");
            out.println("<p>User Role: " + baseAnswer.getUserRole() + "</p>");

            out.println("</body></html>");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        BlogUserRequest ub = new BlogUserRequest();

        ub.setUserName(req.getParameter("userName"));
        ub.setUserMail(req.getParameter("userMail"));
        ub.setFirstLastName(req.getParameter("userFirstLastName"));
        ub.setAboutYourself(req.getParameter("aboutYourself"));
        ub.setUserPassword(req.getParameter("userPassword"));
        //userAvatar
        ub.setDateOfRegister(Timestamp.valueOf(LocalDateTime.now()));

        try {
        BlogUserResponse userResp = dao.addUser(ub);
        if(userResp.isAddUserAnswer()){
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>User added successfully!</body></html>");
                //написать метод который будет вызывать сервлет с выходом на главуню страницу всех постов
                //Метод антона redirect
        } else{
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Error adding user to the database</body></html>");
                // написать логику проверки данных на правильность вводимых данных пользователем
            }

    } catch (IOException | SQLException e) {
        throw new RuntimeException(e);
    }


    }
}
