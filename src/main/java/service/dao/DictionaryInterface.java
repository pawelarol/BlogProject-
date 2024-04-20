package service.dao;

import service.domian.BlogCommentRequest;
import service.domian.BlogPostRequest;
import web.CommandClasses.Post.BlogPostResponse;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryInterface {

    List<BlogPostRequest> getPosts(int page, int pageSize) throws SQLException;
    boolean addComment(BlogCommentRequest blogComment) throws SQLException;
    BlogPostRequest getPostWithComments(long postId) throws SQLException;

    BlogPostResponse addPost(BlogPostRequest postId) throws SQLException;

    List<BlogCommentRequest> getComments(int postId, int page, int pageSize) throws SQLException;

}
