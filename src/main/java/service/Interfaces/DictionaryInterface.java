package service.Interfaces;

import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryInterface {
    List<BlogPost> getPosts(BlogPost postId) throws SQLException;
    void addComment(BlogComment comment) throws SQLException;
    List<BlogPost> getPostWithComments(BlogPost postId) throws SQLException;

    void addPost(BlogPost postId) throws SQLException;

    List<BlogComment> getComment(BlogComment commentId) throws SQLException;

}
