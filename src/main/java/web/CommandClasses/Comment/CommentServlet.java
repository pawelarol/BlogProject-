package web.CommandClasses.Comment;

import persistance.Interfaces.DaoCommentInterface;
import persistance.dao.CommentDao;
import web.domian.BlogCommentRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

// как мне привзяать комментарий к конкретному посту
// необходимо чтобы при вызове поста с комментариями у нас вызывались только первые 10 коментов
// но если пользователь захочет пролистать дальше, то вызовится метод из сервлета комментов
// и ему передасться postTitle и покажуться след 10 комментов
// нужно ли мне писать отдельный метод для того чтобы получить один коммментарий
// и иметь возмоность его редактировать или же я могу напсиать логику в которой
// я смогу выбрать из списка один и тогда его изменять


@WebServlet(name = "BlogComment", urlPatterns = "/Comments")
public class CommentServlet extends HttpServlet {

    private final static long serialVersionUID = 1L;

    private DaoCommentInterface dao;
    private String post;

    public void init() {
        dao = new CommentDao();
        //dao = new CommentDaoInterface;
        post = "";
        // как передать postTitle
        try {
            dao.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private BlogCommentRequest createComment(HttpServletRequest req, String postTitle) {
        BlogCommentRequest comment = new BlogCommentRequest();
        comment.setPostTitle(postTitle);
        comment.setTitle(req.getParameter("commentTitle"));
        comment.setText(req.getParameter("commentText"));
        comment.setDateOfPublish(LocalDateTime.now());
        comment.setUserName(req.getParameter("userName"));
        return comment;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        BlogCommentRequest comment = createComment(req, post);

        try {
            BlogCommentResponse response = dao.addComment(comment, comment.getPostTitle());
            if (response.setAddComAns(true)) {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Data added successfully!</body></html>");
                // call method getPosts with last 10 posts
                // специальный метод для посылания ответа браузера
                resp.sendRedirect("/Comments");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Error adding data to the database</body></html>");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");

        try {

            List<BlogCommentRequest> commentList = dao.getComments(post, 1, 10);

            if (commentList.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>No comments</body></html>");
            } else {
               for(BlogCommentRequest comment : commentList){
                   PrintWriter out = resp.getWriter();
                   resp.setStatus(HttpServletResponse.SC_OK);
                   out.println("<html>");
                   out.println("<head><title>Comment</title></head>");
                   out.println("<body>");
                   out.println("<p><strong>Title:</strong> " + comment.getTitle() + "</p>");
                   out.println("<p><strong>Text:</strong> " + comment.getText() + "</p>");
                   out.println("<p><strong>Publisher:</strong> " + comment.getUserName() + "</p>");
                   out.println("<p><strong>Date of publish:</strong> " + comment.getDateOfPublish() + "</p>");
                   out.println("</body>");
                   out.println("</html>");
               }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        BlogCommentRequest comment = createComment(req, post);

        try{
            BlogCommentResponse blogCommentResponse = dao.deleteComment(comment, post);
            PrintWriter out = resp.getWriter();

            if(blogCommentResponse.isDelComAns() == true){
                resp.setStatus(HttpServletResponse.SC_OK);
                out.println("<html>");
                out.println("<body>");
                out.println("<p><strong>Comment is deleted</strong></p>");
            } else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("<html>");
                out.println("<body>");
                out.println("<p><strong>Something was wrong, please repeat deleteComment</strong></p>");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
