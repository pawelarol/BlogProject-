package BlogProject.DataBase;

import org.junit.Before;
import service.domian.BlogComment;
import service.domian.BlogPost;

import java.time.LocalDateTime;
import java.util.Random;

public class ContentBuilder {

    public static final Random random = new Random();

    public static BlogPost createPost() {
        BlogPost bp = new BlogPost();
        bp.setTitle("Title blog" + random.nextInt(1000));
        bp.setText("Text blog" + random.nextInt(1000));
        bp.setDateOfPublish(LocalDateTime.now());
        bp.setUserName("User post" + random.nextInt(1000));
        return bp;
    }

    public BlogComment createComment(){
            BlogComment bc = new BlogComment();
            bc.setPostId(2);
            bc.setTitle("Title comment "  + random.nextInt(1000));
            bc.setText("Text comment "  + random.nextInt(1000) );
            bc.setDateOfPublish(LocalDateTime.now());
            bc.setUserName("User comment "  + random.nextInt(1000));

        return bc;
    }
}
