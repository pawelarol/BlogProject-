package BlogProject.DataBase;

import web.domian.BlogCommentRequest;
import service.domian.Post;
import service.domian.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

public class ContentBuilder {

    public static final Random random = new Random();

    public Post createPost( ) {
        User user = createUser();
        Post bp = new Post();
        bp.setTitle("Title blog" + random.nextInt(1000));
        bp.setText("Text blog" + random.nextInt(1000));
        bp.setDateOfPublish(LocalDateTime.now());
        return bp;
    }

    public BlogCommentRequest createComment(){
            BlogCommentRequest bc = new BlogCommentRequest();
            bc.setPostId(2);
            bc.setTitle("Title comment "  + random.nextInt(1000));
            bc.setText("Text comment "  + random.nextInt(1000) );
            bc.setDateOfPublish(LocalDateTime.now());
            bc.setUserName("User comment "  + random.nextInt(1000));

        return bc;
    }

    public User createUser() {
        User user = new User();
        user.setUserName("Pavel");
        user.setUserMail("pawelarol@gmail.com");
        user.setUserPassword("12312");
        user.setFirstLastName("Pavel Arol");
        user.setAboutYourself("Hi my name is Pavel. It is my project");
        user.setDateOfRegister(Timestamp.valueOf(LocalDateTime.now()));
        user.setUserRole("Admin");
        return user;
    }
}

