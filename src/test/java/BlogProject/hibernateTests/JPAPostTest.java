package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.Post;
import service.domian.User;

public class JPAPostTest {

    private ContentBuilder builder;
    private JPA jpa;

    private JPAPostTest(){
        jpa = new JPA();
        builder = new ContentBuilder();
    }

    @Test
    public void addPostTest(){
        Post post = builder.createPost();
        User user =  builder.createUser();
        user.setUserId(1);
        Long postAnswer = jpa.addPostJPA(post, user);
        Assertions.assertNotNull(postAnswer);

        System.out.println(postAnswer);
    }

    @Test
    public void getPost(){
        Long postId = 1L;
        Post postJPA = jpa.getPostJPA(postId);
        Assertions.assertNotNull(postJPA);
        System.out.println(postJPA);
    }
}
