package BlogProject.DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.dao.PostDao;
import service.domian.BlogPostRequest;
import service.domian.BlogUserRequest;
import web.CommandClasses.Post.BlogPostResponse;

import java.sql.SQLException;
import java.util.List;

public class PostTests {


//private final static Logger logger = LoggerFactory.getLogger(CommandsTest.class);

    private ContentBuilder builder;
    private PostDao dao;
    private BlogUserRequest user;


    public PostTests() {
        builder = new ContentBuilder();
        dao = new PostDao();
        user = new BlogUserRequest();
    }

    private int pagePost = 1;
    private int pageComment = 1;

    @Test
    public void getPostsTest() throws SQLException {
       //// logger.info("getPosts are started ");
        List<BlogPostRequest> posts = dao.getPosts(pagePost, 5);

        Assertions.assertNotNull(posts, "The list of posts is null");
        Assertions.assertTrue(posts.size() <= 5);

        if (posts.size() == 5) {
            pagePost++;
        }
    }

    @Test
    public void addPostsTest() throws SQLException {
       // logger.info("AddPosts area started");
        BlogPostRequest post = builder.createPost();
        BlogPostResponse ans = dao.addPost(post);
        Assertions.assertNotNull(ans);
    }

    @Test
    public void getOnePost() throws SQLException {
       // logger.info("getOnePost is started ");
        BlogPostRequest ans = dao.getOnePost("FirstPost");
        Assertions.assertNotNull(ans);
    }


}

