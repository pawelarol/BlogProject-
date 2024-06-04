package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.Comment;
import service.domian.Post;
import service.domian.User;

import java.util.List;

public class JPACommentTest {
    private ContentBuilder builder;
    private JPA jpa;

    private JPACommentTest(){
        builder = new ContentBuilder();
        jpa = new JPA();
    }

    @Test
    public void addCommentTest(){
        for(int i = 0; i<10; i++) {
            Comment comment = builder.createComment();
            Post post = new Post();
            post.setPostId(1);
            User user = new User();
            user.setUserId(1);
            Long commentId = jpa.addCommentJPA(comment, post, user);
            Assertions.assertNotNull(commentId);
        }
    }

    @Test
    public void getCommentsTest(){
        List<Comment> comments = jpa.getCommentsJPA(2);
        Assertions.assertNotNull(comments);
        for(Comment c : comments){
            System.out.println(c);
        }
    }

    @Test
    public void getCommentTest(){
        Comment commentRequest = new Comment();
        commentRequest.setCommentId(2);
        Comment comment = jpa.getCommentJPA(commentRequest,2);
        Assertions.assertNotNull(comment);
        System.out.println(comment);
    }
}
