package BlogProject.DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.UserDao;
import web.domian.BlogUserRequest;
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
        BlogUserRequest userReq = new BlogUserRequest();
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
        BlogUserRequest userReq = new BlogUserRequest();
        userReq.setUserName("Pavel");
        BlogUserRequest user = dao.getUser(userReq);
        if (user.getUserName() == "Pavel") {
            Assertions.assertNotNull(user);
        }
    }

    @Test
    public void deleteTest() {
        BlogUserRequest userReq = new BlogUserRequest();
        userReq.setUserName("Pavel");
        BlogUserResponse blogUserResponse = dao.deleteUser(userReq);
        Assertions.assertTrue(blogUserResponse.isDeleteUserAnswer());
    }
}
