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

    @Test
    public void addPostTest(){
        jpa = new JPA();
        builder = new ContentBuilder();
        Post post = builder.createPost();
        User user =  builder.createUser();
        user.setUserId(1);
        Long postAnswer = jpa.addPostJPA(post, user);
        Assertions.assertNotNull(postAnswer);

        System.out.println(postAnswer);
    }
}
