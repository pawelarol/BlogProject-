package BlogProject.hibernateTests;

import BlogProject.DataBase.ContentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistance.dao.hibernateDao.JPA;
import service.domian.Comment;
import service.domian.Post;
import service.domian.User;

public class JPACommentTest {
    private ContentBuilder builder;
    private JPA jpa;

    private JPACommentTest(){
        builder = new ContentBuilder();
        jpa = new JPA();
    }

    @Test
    public void addCommentTest(){
        Comment comment = builder.createComment();
        Post post = new Post();
        post.setPostId(1);
        User user = new User();
        user.setUserId(1);
        Long commentId = jpa.addCommentJPA(comment, post, user);
        Assertions.assertNotNull(commentId);
    }
}
