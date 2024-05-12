package service.Interfaces;

import service.domian.BlogCommentRequest;
import web.CommandClasses.Comment.BlogCommentResponse;

import java.sql.SQLException;
import java.util.List;

public interface DaoCommentInterface {
    BlogCommentResponse addComment(BlogCommentRequest blogComment, String postTitle) throws SQLException;
    List<BlogCommentRequest> getComments(String postTitle, int page, int pageSize) throws SQLException;
    // to write this method in DAO
    BlogCommentRequest getOneComment(BlogCommentRequest commentTitle, String posTitle) throws SQLException;
    BlogCommentResponse deleteComment(BlogCommentRequest commentTitle,String postTitle) throws SQLException;
}
