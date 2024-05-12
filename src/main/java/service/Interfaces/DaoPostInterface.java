package service.Interfaces;

import service.domian.BlogPostRequest;
import web.CommandClasses.Post.BlogPostResponse;

import java.sql.SQLException;
import java.util.List;

public interface DaoPostInterface {
   BlogPostResponse addPost(BlogPostRequest blogPost) throws SQLException;
   List<BlogPostRequest> getPosts(int page, int pageSize) throws SQLException;
   BlogPostRequest getOnePost(String postTitle) throws SQLException;
   BlogPostResponse deletePost(BlogPostRequest post) throws SQLException;

}
