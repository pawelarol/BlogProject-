package persistance.Interfaces;

import service.domian.Comment;
import service.domian.Post;
import service.domian.User;

import java.util.List;

public interface DaoHibernateInterface {
    // posts
    Long addPostJPA(Post post, User user);
    List<Post> getPostsJPA(int page, int pageSize);
    Post getPostJPA(Long postId);
    Long deletePostJPA(Post postId);

    // comments
    Long addCommentJPA(Comment comment, Post post, User user);
    List<Comment> getCommentsJPA(int page, int pageSize, Post postId);
    Comment getCommentJPA(Comment commentId);
    Long deleteCommentJPA(Comment commentId);

    // user

    Long addUserJPA(User user);
    User getUserJPA(User userId);
    Long deleteUserJPA (User userId);

}
