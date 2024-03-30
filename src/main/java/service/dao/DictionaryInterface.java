package service.dao;

import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryInterface {

    List<BlogPost> getPosts( int page, int pageSize) throws SQLException;
    void addComment(List<BlogComment> blogComments) throws SQLException;
    BlogPost getPostWithComments(long postId) throws SQLException;

    void addPost(BlogPost postId) throws SQLException;

    List<BlogComment> getComment(BlogComment commentId) throws SQLException;

    BlogPost getPostWithComments2(int postId, int page, int pageSize) throws SQLException;
}
