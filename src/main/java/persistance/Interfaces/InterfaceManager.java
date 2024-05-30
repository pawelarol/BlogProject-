package persistance.Interfaces;

import service.domian.Post;
import web.CommandClasses.Comment.BlogCommentResponse;
import web.CommandClasses.User.BlogUserResponse;
import web.domian.BlogCommentRequest;
import service.domian.User;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceManager extends DaoCommentInterface, DaoHibernateInterface,
        DaoPostInterface, DaoUserInterface {

    //hibernate



    // comments
    BlogCommentResponse addComment(BlogCommentRequest blogComment, String postTitle) throws SQLException;
    List<BlogCommentRequest> getComments(String postTitle, int page, int pageSize) throws SQLException;
    // to write this method in DAO
    BlogCommentRequest getOneComment(BlogCommentRequest commentTitle, String posTitle) throws SQLException;
    BlogCommentResponse deleteComment(BlogCommentRequest commentTitle,String postTitle) throws SQLException;

    // posts
    Post addPost(Post post) throws SQLException;
    List<Post> getPosts(int page, int pageSize) throws SQLException;
    Post getPost(Post post ) throws SQLException;
    Post deletePost(Post post) throws SQLException;

    //user

    BlogUserResponse addUser(User userBlog);
    User getUser(User userBlog);
    BlogUserResponse deleteUser(User userName);
}
