package BlogProject.DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.CommentDao;
import web.domian.BlogCommentRequest;
import web.CommandClasses.Comment.BlogCommentResponse;

import java.sql.SQLException;
import java.util.List;

public class CommentTests {
    private static int pageComment = 10;
    private CommentDao dao;
    private ContentBuilder builder;

    private String post;

    private String userName;

    public void setUserName(String userName) {
        this.userName = "Pavel";
    }

    public CommentTests(){
        dao = new CommentDao();
        builder = new ContentBuilder();
        post = ("First Post");
    }

    @Test
    public void addCommentTest() throws SQLException {
       // logger.info("AddComment is started ");
        //BlogCommentRequest comment = builder.createComment();
      //  BlogCommentResponse ans = dao.addComment(comment, post);
       // Assertions.assertNotNull(ans);
    }


    @Test
    public void getCommentsTest() {
        //logger.info("getComments are started ");
        List<BlogCommentRequest> comments = dao.getComments(post, pageComment, 5);

        Assertions.assertNotNull(comments, "The list of posts is null");
        Assertions.assertTrue(comments.size() <= 5);

        if (comments.size() > 5) {
            pageComment++;
            getCommentsTest();
        }
    }

    @Test
    public void getOneComment() throws SQLException {
//        получить пост
//                получить список комментариев
//                выбрать нужный комментарий
//                проверить есть ли доступ к комментарию по Имени пользователя
//                изменить комментарий

        // написать логику замены данных о коментарий, где это лучше написать в
        // в бизнесе или в дао

        List<BlogCommentRequest> comments = dao.getComments(post, pageComment, 10);
        for(BlogCommentRequest com : comments){
            if(userName == com.getUserName()){
                BlogCommentResponse response = dao.addComment(com, post);
                Assertions.assertTrue(response.isAddComAns());
            } else {
                System.out.println("It's not you comment");
            }
        }
    }

}
