package BlogProject.DataBase;

import org.junit.Before;
import service.domian.BlogComment;
import service.domian.BlogPost;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContentBuilder {
   @Before
    public List<BlogPost> createPost(){
        List<BlogPost> postList = new ArrayList<>();
        for(int i = 0; i < 5; i++ ) {

            BlogPost bp = new BlogPost();
            bp.setPostId(0 + i);
            bp.setTitle("Title blog" + i);
            bp.setText("Text blog" + i);
            bp.setDateOfPublish(LocalDateTime.now());
            bp.setUserName("User post" + i);

            postList.add(bp);
        }
        return postList;
    }

    @Before
    public List<BlogComment> createComments(){
        List<BlogComment> commentList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            BlogComment bc = new BlogComment();
            bc.setPostId(0 + i);
            bc.setTitle("Title comment " + i);
            bc.setText("Text comment " + i);
            bc.setDateOfPublish(LocalDateTime.now());
            bc.setUserName("User comment " + i);

            commentList.add(bc);
        }
        return commentList;
    }
}
