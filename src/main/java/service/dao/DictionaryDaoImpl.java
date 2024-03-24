package service.dao;

import service.Interfaces.DictionaryInterface;
import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DictionaryDaoImpl implements DictionaryInterface {

    private static final String GET_POSTS = "SELECT post_id, post_title, post_text, dateofpublish " +
            "FROM public.bl_post " +
            "OFFSET 10 " +
            "LIMIT 10;";

    private static final String ADD_COMMENT = "INSERT INTO public.bl_comment(" +
            "post_id, comment_id, comment_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String ADD_POST = "INSERT INTO public.bl_post(" +
            "post_id, post_title, post_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String GET_COMMENT= "SELECT post_id, comment_id, comment_text, publicist_name, dateofpublish " +
            "FROM public.bl_comment " +
            "OFFSET 10 " +
            "LIMIT 10;";;

    public Connection getConnect() throws SQLException {
        return BuilderConnection.getConnection();
    }


    @Override
    public List<BlogPost> getPosts(BlogPost postId) {
        List<BlogPost> answer = new ArrayList<>();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_POSTS)){
            stmt.setLong(1, postId.getPostId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                    BlogPost bp = new BlogPost();
                    bp.setPostId(rs.getLong("post_id"));
                    bp.setTitle(rs.getString("post_title"));
                    bp.setText(rs.getString("post_text"));
                    bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
                    answer.add(bp);

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return answer;
    }

    @Override
    public void addComment(BlogComment comment) {
        try(Connection con = getConnect();
        PreparedStatement stmt = con.prepareStatement(ADD_COMMENT)){
            stmt.setLong(1,comment.getCommentId());
            stmt.setString(2,comment.getText());
            stmt.setString(3,comment.getUserName());
            stmt.setObject(4,comment.getDateOfPublish());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A new comment has been inserted successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<BlogPost> getPostWithComments(BlogPost postId) {
       List<BlogPost> posts = new ArrayList<>();
        List<BlogComment> comments = new ArrayList<>();

        try (Connection con = getConnect();
             PreparedStatement postStmt = con.prepareStatement(GET_POSTS);
             PreparedStatement commentsStmt = con.prepareStatement(GET_COMMENT)) {

            postStmt.setLong(1, postId.getPostId());
            ResultSet postRs = postStmt.executeQuery();

            if (postRs.next()) {
                BlogPost bp = new BlogPost();
                bp.setTitle(postRs.getString("post_title"));
                bp.setText(postRs.getString("post_text"));
                bp.setDateOfPublish(postRs.getObject("dateOfpublish", LocalDateTime.class));
                posts.add(bp);
            }

            commentsStmt.setLong(1, postId.getPostId());
            ResultSet commentsRs = commentsStmt.executeQuery();

            while (commentsRs.next()) {
                    BlogComment bc = new BlogComment();
                    bc.setPostId(commentsRs.getLong("comment_id"));
                    bc.setText(commentsRs.getString("comment_text"));
                    bc.setDateOfPublish(commentsRs.getObject("dateOfpublish", LocalDateTime.class));
                    comments.add(bc);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return posts;
    }

    @Override
    public void addPost(BlogPost blogPost) {
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(ADD_POST)){
            stmt.setLong(1,blogPost.getPostId());
            stmt.setString(2,blogPost.getTitle());
            stmt.setString(3,blogPost.getText());
            stmt.setString(4,blogPost.getUserName());
            stmt.setObject(5,blogPost.getDateOfPublish());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A new post has been inserted successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<BlogComment> getComment(BlogComment commentId) {
        List<BlogComment> answer = new ArrayList<>();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_COMMENT)){
            stmt.setLong(1, commentId.getPostId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                BlogComment bc = new BlogComment();
                bc.setPostId(rs.getLong(""));
                bc.setText(rs.getString(""));
                bc.setUserName(rs.getString(""));
                bc.setDateOfPublish(rs.getObject("", LocalDateTime.class));
                answer.add(bc);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return answer;
    }
}
