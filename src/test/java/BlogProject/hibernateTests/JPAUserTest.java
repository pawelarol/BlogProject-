package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Test
    public void getUser() {
        long userId = 3L;
        User user = jpa.getUserJPA(userId);
        if (user.getUserId() == userId) {
            Assertions.assertNotNull(user);
            System.out.println(user);
        } else {
            Assertions.fail("User not founded.");
        }
    }


    @Test
    public void deleteUser(){
        User user =  new User();
        user.setUserId(3L);
        user.setUserName("Pavel");
        user.setUserMail("pawelarol@gmail.com");
        user.setUserPassword("12312");
        user.setFirstLastName("Pavel Arol");
        user.setAboutYourself("Hi my name is Pavel. It is my project");
        user.setDateOfRegister(Timestamp.valueOf(LocalDateTime.now()));
        user.setUserRole("Admin");

        User userAnswer = jpa.deleteUserJPA(user);
        Assertions.assertNull(userAnswer);
    }
}
