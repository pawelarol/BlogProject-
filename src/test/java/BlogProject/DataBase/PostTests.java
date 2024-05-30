package BlogProject.DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistance.dao.PostDao;
import service.domian.Post;
import service.domian.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class PostTests {


private final static Logger logger = LoggerFactory.getLogger(PostTests.class);
private final static Random random = new Random();

    private ContentBuilder builder;
    private PostDao dao;
    private User user;


    public PostTests() {
        builder = new ContentBuilder();
        dao = new PostDao();
        user = new User();
    }


    @Test
    public void getPosts() throws SQLException {
        logger.info("GetPosts is started");
        int pageNumber = 3;
        int pageSize = 10;
        List<Post> postList =  dao.getPosts(pageNumber, pageSize);
        if(postList.isEmpty()){
            System.out.println("no data");
        } else {
            for (Post p : postList) {
                System.out.println(p.toString());
            }
        }

    }

    @Test
    public void addPostsTest() {
            // logger.info("AddPosts area started");

            for(int i = 0; i<50; i++) {
                Post post = builder.createPost();
                Post postResp = dao.addPost(post);
                Assertions.assertNotNull(postResp);
                System.out.println(postResp);
            }
        }

    @Test
    public void getOnePost() throws SQLException {
       // logger.info("getOnePost is started ");
        Post post = new Post();
        post.setPostId(3);
        Post ans = dao.getPost(post);
        Assertions.assertNotNull(ans);
    }

    @Test
    public void deletePostTest() throws SQLException {
        int counterDELPost = 0;
        Post post = new Post();
        post.setPostId(10);
        Post ans = dao.deletePost(post);
      //  int statusPost = ans.getStatusPost();
        //if (statusPost == ObjectStatus.DELETED){
            counterDELPost++;
        }

        //System.out.println(counterDELPost);

    }

//    public void deletePostTest1() throws SQLException {
//        int counterDELPost = 0;
//        Post post = new Post();
//        long postIdRandom = random.nextLong(100);
//        post.setPostId(postIdRandom);
//
//        Post ans = dao.deletePost(post);
//        int statusPost = ans.getStatusPost();
//
//        if (statusPost == ObjectStatus.DELETED) {
//            counterDELPost++;
//        }
//
//        // Проверка, что статус поста установлен как DELETED
//        Assertions.assertEquals(ObjectStatus.DELETED, statusPost);
//
//        // Проверка, что пост действительно удален из базы данных
//        try (Connection con = dao.getConnect();
//             PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM bl_post WHERE post_id = ?")) {
//            stmt.setLong(1, post.getPostId());
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    int count = rs.getInt(1);
//                    assertEquals(0, count, "Пост не был удален из базы данных");
//                }
//            }
//        }
//
//        System.out.println(counterDELPost);
//    }


