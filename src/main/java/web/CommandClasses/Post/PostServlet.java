package web.CommandClasses.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;;
import service.domian.BlogPostRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "addBlogPost", urlPatterns = "/addPost")
public class PostServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private DictionaryDaoImpl dao;


    public void init()  {
        logger.info("BlogPostServlet is created");
        dao = new DictionaryDaoImpl();

        //Try to create is the best logic there
        try {
            dao.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        BlogPostRequest blogReq = new BlogPostRequest();
        blogReq.setTitle(req.getParameter("Title"));
        blogReq.setText(req.getParameter("Text"));
        blogReq.setUserName(req.getParameter("UserName"));
        //be written logic for user
     //   blogReq.setUserName(req.getParameter("User Name: "));
        blogReq.setDateOfPublish(LocalDateTime.now());

        BlogPostResponse postResponse = dao.addPost(blogReq);

        if(postResponse.isAddPostAnswer()){
           resp.setContentType("text/html");
           resp.getWriter().println("<html><body>Data added successfully!</body></html>");
       } else{
           resp.setContentType("text/html");
           resp.getWriter().println("<html><body>Error adding data to the database</body></html>");
       }
    }



}

