package web.CommandClasses.User;

import persistance.dao.UserDao;
import web.domian.UserRequest;
import web.Interfaces.ServletManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@WebServlet(name = "BlogUser", urlPatterns = "/User")
public class UserServlet extends ServletManager {

    private final static long serialVersionUID = 1L;
    private UserDao dao;

    public void init(){
        dao = new UserDao();
        try {
            dao.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        UserRequest userRequest = new UserRequest();
       ////  userRequest.setUserName(req.getParameter("userName"));

        try {

           UserRequest baseAnswer = null; //stub

            if(baseAnswer == null) {
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
                resp.setStatus(HttpServletResponse.SC_OK);

               } else{
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>User not found</body></html>");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        UserRequest ub = new UserRequest();

      //  ub.setUserName(req.getParameter("userName"));
        ub.setUserMail(req.getParameter("userMail"));
        ub.setFirstLastName(req.getParameter("userFirstLastName"));
        ub.setAboutYourself(req.getParameter("aboutYourself"));
        ub.setUserPassword(req.getParameter("userPassword"));
        //userAvatar
        ub.setDateOfRegister(Timestamp.valueOf(LocalDateTime.now()));

        try {
        BlogUserResponse userResp = null; // заглушка
        if(userResp.isAddUserAnswer()){
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>User added successfully!</body></html>");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                //написать метод который будет вызывать сервлет с выходом на главуню страницу всех постов
                //Метод антона redirect
        } else{
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Error adding user to the database</body></html>");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                // написать логику проверки данных на правильность вводимых данных пользователем
            }

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        UserRequest user = new UserRequest();
       // user.setUserName(req.getParameter("userName"));

        try{
            BlogUserResponse respData = null; // stub
            PrintWriter out = resp.getWriter();

            if(respData.setDeleteUserAnswer(true)) {
                out.println("<html>");
                out.println("<body>");
                out.println("<p><strong>User is deleted</strong></p>");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                out.println("<html>");
                out.println("<body>");
                out.println("<p><strong>Something was wrong, please repeat deleteUser</strong></p>");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
