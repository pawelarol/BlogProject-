package service.dao;

import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DictionaryDaoImpl implements DictionaryInterface {

    private static final String ADD_POST = "INSERT INTO public.bl_post(" +
            " post_title, post_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?) ";

    private static final String GET_POSTS = "SELECT * FROM public.bl_post ";
    private static final String GET_POST = "SELECT post_id, post_title, post_text, dateofpublish " +
            "FROM public.bl_post " +
            "WHERE post_id = ?";

    private static final String ADD_COMMENT = "INSERT INTO public.bl_comment(" +
            "post_id, comment_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?) ";
    private static final String GET_COMMENT = "SELECT * FROM public.bl_comment " +
            "WHERE post_id = ?";

    private static final String GET_PWC = "SELECT bl_post.*, bl_comment.* " +
            "FROM bl_post " +
            "LEFT JOIN bl_comment ON bl_post.post_id = bl_comment.post_id " +
            "WHERE bl_post.post_id = ?";


    public Connection getConnect() throws SQLException {
        return BuilderConnection.getConnection();
    }


    @Override
    public boolean addPost(BlogPost blogPost) {
        boolean result = false;
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_POST)) {
            stmt.setString(1, blogPost.getTitle());
            stmt.setString(2, blogPost.getText());
            stmt.setString(3, blogPost.getUserName());
            stmt.setObject(4, blogPost.getDateOfPublish());

            int answer = stmt.executeUpdate();

            if (answer > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<BlogPost> getPosts(int page, int pageSize) {
        List<BlogPost> answer = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        if (page <= 0 || pageSize <= 0) {
            System.out.println("Fatal error 404!");
        } else {
            int offset = (page - 1) * pageSize;

            try (Connection con = getConnect();
                 PreparedStatement stmt = con.prepareStatement(GET_POSTS)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    BlogPost bp = new BlogPost();
                    bp.setPostId(rs.getLong("post_id"));
                    bp.setTitle(rs.getString("post_title"));
                    bp.setText(rs.getString("post_text"));
                    bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
                    answer.add(bp);
                }

                startIndex = Math.min(offset, answer.size());
                endIndex = Math.min(offset + pageSize, answer.size());
                List<BlogPost> pagePosts = new ArrayList<>();

                for (int i = offset; i < Math.min(offset + pageSize, answer.size()); i++) {
                    pagePosts.add(answer.get(i));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return answer.subList(startIndex, endIndex);
    }

    public BlogPost getOnePost(long postId) throws SQLException {
        BlogPost bp = new BlogPost();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(GET_POST)) {
            stmt.setLong(1, postId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bp.setPostId(rs.getLong("post_id"));
                bp.setTitle(rs.getString("post_title"));
                bp.setText(rs.getString("post_text"));
                bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
            }
            return bp;
        }
    }

    @Override
    public boolean addComment(BlogComment blogComments) throws SQLException {
        boolean result = false;
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_COMMENT)) {
            stmt.setLong(1, blogComments.getPostId());
            stmt.setString(2, blogComments.getText());
            stmt.setString(3, blogComments.getUserName());
            stmt.setObject(4, blogComments.getDateOfPublish());

            int successCounter = stmt.executeUpdate();
            if (successCounter > 0) {
                result = true;
            }
        }
        return result;
    }


    @Override
    public List<BlogComment> getComments(int postId, int page, int pageSize) {
        List<BlogComment> answer = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        if (page <= 0 || pageSize <= 0) {
            System.out.println("Fatal error 404!");
        } else {
            int offset = (page - 1) * pageSize;
            try (Connection con = getConnect();
                 PreparedStatement stmt = con.prepareStatement(GET_COMMENT)) {
                stmt.setInt(1, postId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    BlogComment bc = new BlogComment();
                    bc.setPostId(rs.getInt("post_id"));
                    bc.setCommentId(rs.getLong("comment_id"));
                    bc.setText(rs.getString("comment_text"));
                    bc.setUserName(rs.getString("publicist_name"));
                    bc.setDateOfPublish(rs.getObject("dateofpublish", LocalDateTime.class));
                    answer.add(bc);
                }

                startIndex = Math.min(offset, answer.size());
                endIndex = Math.min(offset + pageSize, answer.size());
                List<BlogPost> pagePosts = new ArrayList<>();

                for (int i = offset; i < Math.min(offset + pageSize, answer.size()); i++) {
                    pagePosts.add(answer.get(i));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        return answer.subList(startIndex, endIndex);
    }


    @Override
    public BlogPost getPostWithComments(long postId) throws SQLException {
        BlogPost bp = new BlogPost();
        BlogComment bc = new BlogComment();
        List<BlogComment> commentList = new ArrayList<>();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(GET_PWC)) {
            stmt.setLong(1, postId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bp.setPostId(rs.getLong("post_id"));
                bp.setTitle(rs.getString("post_title"));
                bp.setText(rs.getString("post_text"));
                bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));

                bc.setPostId(rs.getInt("post_id"));
                bc.setCommentId(rs.getLong("comment_id"));
                bc.setText(rs.getString("comment_text"));
                bc.setUserName(rs.getString("publicist_name"));
                bc.setDateOfPublish(rs.getObject("dateofpublish", LocalDateTime.class));
                commentList.add(bc);
                bp.setComments(commentList);
            }
            if (rs != null) {
                return bp;
            } else {
                return null;
            }
        }
    }
}

