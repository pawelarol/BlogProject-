package BlogProject.DataBase;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import service.domian.BlogComment;
import service.domian.BlogPost;
import java.sql.SQLException;
import java.util.List;

public class CommandsTest {

    private static final Logger logger = LoggerFactory.getLogger(CommandsTest.class);
    ContentBuilder builder = new ContentBuilder();
    DictionaryDaoImpl dao = new DictionaryDaoImpl();
    private int pagePost = 1;
    private int pageComment = 1;

    @Test
    public void getPostsTest() {
        logger.info("getPosts are started ");
        List<BlogPost> posts = dao.getPosts(pagePost, 5);

        Assertions.assertNotNull(posts, "The list of posts is null");
        Assertions.assertTrue(posts.size() <= 5);

        if (posts.size() == 5) {
            pagePost++;
        }
    }

    @Test
    public void addPostsTest() {
        logger.info("AddPosts area started");
        BlogPost post = builder.createPost();
        boolean ans = dao.addPost(post);
        Assertions.assertTrue(ans);
    }

    @Test
    public void getOnePost() throws SQLException {
        logger.info("getOnePost is started ");
        BlogPost ans = dao.getOnePost(1);
        Assertions.assertNotNull(ans);
    }

    @Test
    public void addCommentTest() throws SQLException {
        logger.info("AddComment is started ");
        BlogComment comment = builder.createComment();
        boolean ans = dao.addComment(comment);
        Assertions.assertTrue(ans);
    }


    @Test
    public void getCommentsTest() {
        logger.info("getComments are started ");
        List<BlogComment> comments = dao.getComments(2, pageComment, 5);

        Assertions.assertNotNull(comments, "The list of posts is null");
        Assertions.assertTrue(comments.size() <= 5);

        if (comments.size() > 5) {
            pageComment++;
            getCommentsTest();
        }
    }


    @Test
    public void getPostWithCommentsTest() throws SQLException {
        logger.info("Get post W Comments is started");
        BlogPost post = dao.getPostWithComments(1);
        if (post.getComments() == null) getOnePost();
        Assertions.assertNotNull(post);
        List<BlogComment> comments = post.getComments();
        //не показывает postID
        Assertions.assertNotNull(post);
        Assertions.assertNotNull(comments);
    }
}

