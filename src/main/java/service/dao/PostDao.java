package service.dao;

import service.Interfaces.DaoPostInterface;
import service.domian.BlogPostRequest;
import web.CommandClasses.Post.BlogPostResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements DaoPostInterface {


    public Connection getConnect() throws SQLException{
        return BuilderConnection.getConnection();
    }


    private static final String ADD_POST = "INSERT INTO public.bl_post(" +
            " post_title, post_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?) ";

    private static final String GET_POSTS = "SELECT * FROM public.bl_post ";
    private static final String GET_POST = "SELECT post_id, post_title, post_text, dateofpublish " +
            "FROM public.bl_post " +
            "WHERE post_title = ?";

    private static final String DELETE_POST = "DELETE * FROM public.bl_post where post_title = ?" ;

    @Override
    public BlogPostResponse addPost(BlogPostRequest blogPost) throws SQLException {
        BlogPostResponse response = new BlogPostResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_POST)) {
            stmt.setString(1, blogPost.getTitle());
            stmt.setString(2, blogPost.getText());
            stmt.setString(3, blogPost.getUserName());
            stmt.setObject(4, blogPost.getDateOfPublish());

            int answer = stmt.executeUpdate();

            if (answer > 0) {
                response.setAddPostAnswer(true);
            } else {
                response.setAddPostAnswer(false);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return response;
    }

    @Override
    public List<BlogPostRequest> getPosts(int page, int pageSize) throws SQLException {
        List<BlogPostRequest> answer = new ArrayList<>();
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
                    BlogPostRequest bp = new BlogPostRequest();
                    bp.setPostId(rs.getLong("post_id"));
                    bp.setTitle(rs.getString("post_title"));
                    bp.setText(rs.getString("post_text"));
                    bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
                    answer.add(bp);
                }

                startIndex = Math.min(offset, answer.size());
                endIndex = Math.min(offset + pageSize, answer.size());
                List<BlogPostRequest> pagePosts = new ArrayList<>();

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
    public BlogPostRequest getOnePost(String postTitle) throws SQLException {
        BlogPostRequest bp = new BlogPostRequest();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(GET_POST)) {
            stmt.setString(1, postTitle);
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
    public BlogPostResponse deletePost(BlogPostRequest post) throws SQLException {
        BlogPostResponse postResp = new BlogPostResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(DELETE_POST)) {
            stmt.setString(1, post.getTitle());
            ResultSet ans = stmt.executeQuery();
            if (ans.next()) {
                postResp.setDeletePostAnswer(true);
            } else {
                postResp.setDeletePostAnswer(false);
            }
        }
        return postResp;
    }
}
