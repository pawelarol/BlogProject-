package web.CommandClasses.Post;
;
import service.dao.PostDao;
import service.domian.BlogPostRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;

// вопросы по разработке: // спросить про написание исключения для функционала
// придумать функционал для метода doHead
//     Обработка ошибок: Добавьте обработку возможных ошибок, таких как отсутствие параметров в запросе или ошибки при получении данных из базы данных. Вместо простого RuntimeException можно использовать более специфичные исключения.
//
//       Обработка списка комментариев: Получите список комментариев для выбранного поста из базы данных и отобразите его на странице. Можно использовать цикл для прохода по каждому комментарию и отображения его в HTML-формате.
//
//    Добавление кэширования: Если ваша страница редко меняется, рассмотрите возможность добавления кэширования для уменьшения нагрузки на сервер и улучшения производительности.
//
//    Добавление защиты от CSRF и XSS: Обеспечьте защиту вашего приложения от атак типа CSRF (межсайтовая подделка запроса) и XSS (межсайтовый скриптинг).

@WebServlet(name = "BlogPost", urlPatterns = "/Posts")
public class PostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //  private final static Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private PostDao dao;


    public void init()  {
        //logger.info("BlogPostServlet is created");
      dao = new PostDao();

        //Try to create is the best logic there
        try {
            dao.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private BlogPostRequest createPost(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");


        BlogPostRequest post = new BlogPostRequest();
        post.setTitle(req.getParameter("postTitle"));
        post.setText(req.getParameter("postText"));
        post.setUserName(req.getParameter("userName"));
        post.setDateOfPublish(LocalDateTime.parse(req.getParameter("dateOfPublish")));
        //be written logic for user
        //   blogReq.setUserName(req.getParameter("User Name: "));

        return post;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        BlogPostRequest blogReq = createPost(req);
        try {

            BlogPostResponse postResponse = dao.addPost(blogReq);

            if (postResponse.isAddPostAnswer()) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Data added successfully!</body></html>");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("text/html");
                resp.getWriter().println("<html><body>Error adding data to the database</body></html>");
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        req.setCharacterEncoding("UTF-8");


        BlogPostRequest post = createPost(req);
        // узнать как получать список комментариев к посту

        try {
            BlogPostRequest ans = dao.getOnePost(post.getTitle());

                if (ans != null) {
                    PrintWriter out = resp.getWriter();
                    out.println("<html>");
                    out.println("<head><title>Post</title></head>");
                    out.println("<body>");
                    out.println("<p><strong>Title:</strong> " + ans.getTitle() + "</p>");
                    out.println("<p><strong>Text:</strong> " + ans.getText() + "</p>");
                    out.println("<p><strong>Publisher:</strong> " + ans.getUserName() + "</p>");
                    out.println("<p><strong>Date of publish:</strong> " + ans.getDateOfPublish() + "</p>");
                    // список комментариев
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    // спросить про написание исключения для функционала какие лучше использовать или же написать собственные
                    PrintWriter out = resp.getWriter();
                    out.println("<html>");
                    out.println("<head><title>This post has not found.</title></head>");

                }


            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        }

        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {

            BlogPostRequest request = createPost(req);

            try{
                BlogPostResponse blogPostResponse = dao.deletePost(request);
                PrintWriter out = resp.getWriter();

                if(blogPostResponse.isDeletePostAnswer() == true){
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<p><strong>Post is deleted</strong></p>");
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<p><strong>Something was wrong, please repeat deletePost</strong></p>");
                }
            } catch (SQLException | IOException ex){
                ex.printStackTrace();
            }


        }


}

