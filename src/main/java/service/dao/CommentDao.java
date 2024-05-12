package service.dao;

import service.Interfaces.DaoCommentInterface;
import service.domian.BlogCommentRequest;
import web.CommandClasses.Comment.BlogCommentResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements DaoCommentInterface {

    private static final String ADD_COMMENT = "INSERT INTO public.bl_comment(" +
            "post_title, comment_title, comment_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?, ?) ";
    private static final String GET_COMMENTS = "SELECT * FROM public.bl_comment " +
            "WHERE post_title = ?";

    private static final String GET_ONE_COMMENT = "SELECT * FROM public.bl_comment " +
            "WHERE comment_title = ? AND post_title = ?";

    private static final String DELETE_COMMENT = "delete from public.bl_comment " +
            "where comment_title = ? and post_title = ?";


    public Connection getConnect() throws SQLException {
        return BuilderConnection.getConnection();
    }


    @Override
    public BlogCommentResponse addComment(BlogCommentRequest blogComment, String postTitle) throws SQLException {
        BlogCommentResponse answer = new BlogCommentResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_COMMENT)) {
            stmt.setString(1, blogComment.getTitle());
            stmt.setString(2, blogComment.getText());
            stmt.setString(3, blogComment.getUserName());
            stmt.setObject(4, blogComment.getDateOfPublish());

            int successCounter = stmt.executeUpdate();
            if (successCounter > 0) {
                answer.setAddComAns(true);
            } else {
                answer.setAddComAns(false);
            }
        }
        return answer;
    }

    @Override
    public List<BlogCommentRequest> getComments(String postTitle, int page, int pageSize) {
        List<BlogCommentRequest> answer = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        if (page <= 0 || pageSize <= 0) {
            System.out.println("Fatal error 404!");
        } else {
            int offset = (page - 1) * pageSize;
            try (Connection con = getConnect();
                 PreparedStatement stmt = con.prepareStatement(GET_COMMENTS)) {
                stmt.setString(1, postTitle);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    BlogCommentRequest bc = new BlogCommentRequest();
                    bc.setPostTitle(rs.getString("post_title"));
                    bc.setCommentId(rs.getLong("comment_id"));
                    bc.setText(rs.getString("comment_text"));
                    bc.setUserName(rs.getString("publicist_name"));
                    bc.setDateOfPublish(rs.getObject("dateofpublish", LocalDateTime.class));
                    answer.add(bc);
                }

                startIndex = Math.min(offset, answer.size());
                endIndex = Math.min(offset + pageSize, answer.size());
                List<BlogCommentRequest> pagePosts = new ArrayList<>();

                for (int i = offset; i < Math.min(offset + pageSize, answer.size()); i++) {
                    pagePosts.add(answer.get(i));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        return answer.subList(startIndex, endIndex);
    }

    public BlogCommentRequest getOneComment(BlogCommentRequest commentTitle, String postTitle) throws SQLException {
        BlogCommentRequest comment = new BlogCommentRequest();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(GET_ONE_COMMENT)) {
            stmt.setString(1, postTitle);
            stmt.setString(2, commentTitle.getTitle());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comment.setPostTitle(rs.getString("post_title"));
                comment.setTitle(rs.getString("post_title"));
                comment.setText(rs.getString("post_text"));
                comment.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
            }
            return comment;
        }
    }

    @Override
    public BlogCommentResponse deleteComment(BlogCommentRequest commentTitle, String postTitle) throws SQLException {
        BlogCommentResponse response = new BlogCommentResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(DELETE_COMMENT)) {
            stmt.setString(1, postTitle);
            stmt.setString(2, commentTitle.getTitle());
            int answer = stmt.executeUpdate();

            if (answer > 0) {
                response.setDelComAns(true);
            } else {
                response.setDelComAns(false);
            }

            return response;
        }
    }
}