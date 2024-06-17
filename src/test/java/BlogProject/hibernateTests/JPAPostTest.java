package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.Post;
import service.domian.User;

import java.util.List;

public class JPAPostTest {


    private ContentBuilder builder;
    private JPA jpa;

    private JPAPostTest(){
        jpa = new JPA();
        builder = new ContentBuilder();
    }

    @Test
    public void addPostTest(){
        for(int i = 0; i<30; i++) {
            Post post = builder.createPost();
            User user = builder.createUser();
            user.setUserId(1);
            Long postAnswer = jpa.addPostJPA(post, user);
            Assertions.assertNotNull(postAnswer);

            System.out.println(postAnswer);
        }
    }


    //не работает
    @Test
    public void getPostTest(){
        Long postId = 1L;
        Post postJPA = jpa.getPostJPA(postId);
        Assertions.assertNotNull(postJPA);
        System.out.println(postJPA);
    }

    @Test
    public void deletePostTest(){
        Post post = new Post();
        post.setPostId(2);
        Long postId = jpa.deletePostJPA(post);
        Assertions.assertNotNull(postId);
    }


    // не работает
    @Test
    public void getPostsTest(){
        List<Post> posts = jpa.getPostsJPA(1);
        Assertions.assertNotNull(posts);
        for(Post p : posts){
            System.out.println(p);
        }
    }
}
