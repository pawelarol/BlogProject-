package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.User;

public class JPAUserTest {

    private ContentBuilder builder;
    private JPA jpa;

    private JPAUserTest(){
        builder = new ContentBuilder();
        jpa = new JPA();
    }
    @Test
    public void addUserTest(){
        User user = builder.createUser();
        Long userId = jpa.addUserJPA(user);
        Assertions.assertNotNull(userId, "User is added");

    }
}
