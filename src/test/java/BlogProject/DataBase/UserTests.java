package BlogProject.DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.UserDao;
import service.domian.User;
import web.CommandClasses.User.BlogUserResponse;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserTests {

    private UserDao dao;

    public UserTests() {
        dao = new UserDao();
    }

    @Test
    public void addTest() {
        User userReq = new User();
        userReq.setUserName("Pavel");
        userReq.setUserMail("pawelarol@gmail.com");
        userReq.setUserPassword("1231");
        userReq.setFirstLastName("PavelArol");
        userReq.setAboutYourself("Hi my name is Pavel I wanna be a rich");
        // avatar
        userReq.setDateOfRegister(Timestamp.valueOf(LocalDateTime.now()));
        // userPosts, userComments list
        userReq.setUserRole("Admin");

        BlogUserResponse blogUserResponse = dao.addUser(userReq);
        Assertions.assertTrue(blogUserResponse.isAddUserAnswer());
    }

    @Test
    public void getTest(){
        User userReq = new User();
        userReq.setUserName("Pavel");
        User user = dao.getUser(userReq);
        if (user.getUserName() == "Pavel") {
            Assertions.assertNotNull(user);
        }
    }

    @Test
    public void deleteTest() {
        User userReq = new User();
        userReq.setUserName("Pavel");
        BlogUserResponse blogUserResponse = dao.deleteUser(userReq);
        Assertions.assertTrue(blogUserResponse.isDeleteUserAnswer());
    }
}
