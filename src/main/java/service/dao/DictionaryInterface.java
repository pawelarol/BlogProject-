package service.dao;

import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryInterface {

    List<BlogPost> getPosts(int page, int pageSize) throws SQLException;
    boolean addComment(BlogComment blogComment) throws SQLException;
    BlogPost getPostWithComments(long postId) throws SQLException;

    boolean addPost(BlogPost postId) throws SQLException;

    List<BlogComment> getComments(int postId, int page, int pageSize) throws SQLException;

}
