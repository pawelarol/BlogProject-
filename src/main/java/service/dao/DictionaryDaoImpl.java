package service.dao;

import service.domian.BlogCommentRequest;
import service.domian.BlogPostRequest;
import service.domian.BlogUserRequest;
import web.CommandClasses.Post.BlogPostResponse;
import web.CommandClasses.User.BlogUserResponse;

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

    private static final String ADD_USER = "INSERT INTO public.bl_user " +
            "(user_name, user_mail, user_password, user_about_yourself," +
            " user_first_last_name, user_role, dateOfRegister)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER = "SELECT * from public.bl_user " +
            "where user_name = ?";

    private static final String DELETE_USER = "DELETE FROM bl_user " +
            "WHERE user_name = ? ";


    public Connection getConnect() throws SQLException {
        return BuilderConnection.getConnection();
    }


    @Override
    public BlogPostResponse addPost(BlogPostRequest blogPost) {
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
    public List<BlogPostRequest> getPosts(int page, int pageSize) {
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

    public BlogPostRequest getOnePost(long postId) throws SQLException {
        BlogPostRequest bp = new BlogPostRequest();
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
    public boolean addComment(BlogCommentRequest blogComments) throws SQLException {
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
    public List<BlogCommentRequest> getComments(int postId, int page, int pageSize) {
        List<BlogCommentRequest> answer = new ArrayList<>();
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
                    BlogCommentRequest bc = new BlogCommentRequest();
                    bc.setPostId(rs.getInt("post_id"));
                    bc.setCommentId(rs.getLong("comment_id"));
                    bc.setText(rs.getString("comment_text"));
                    bc.setUserName(rs.getString("publicist_name"));
                    bc.setDateOfPublish(rs.getObject("dateofpublish", LocalDateTime.class));
                    answer.add(bc);
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
    public BlogUserResponse addUser(BlogUserRequest userBlog) throws SQLException {
        BlogUserResponse response = new BlogUserResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_USER)) {

            stmt.setString(1, userBlog.getUserName());
            stmt.setString(2, userBlog.getUserMail());
            stmt.setString(3, userBlog.getUserPassword());
            stmt.setString(4, userBlog.getFirstLastName());
            stmt.setString(5, userBlog.getAboutYourself());
            stmt.setString(6, userBlog.getUserRole());
            stmt.setTimestamp(7, userBlog.getDateOfRegister());

            int answer = stmt.executeUpdate();

            if (answer > 0) {
                response.setAddUserAnswer(true);
            } else {
                response.setAddUserAnswer(false);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return response;
    }

    @Override
    public BlogUserRequest getUser(BlogUserRequest userName) throws SQLException {
        BlogUserRequest userReq = new BlogUserRequest();
        try(Connection con = getConnect();
        PreparedStatement stmt = con.prepareStatement(GET_USER)){
            stmt.setString(1, userName.getUserName());
            ResultSet rs = stmt.executeQuery();
                while (rs.next()){

                    userReq.setUserName(rs.getString("user_name"));
                    userReq.setUserMail(rs.getString("user_mail"));
                    userReq.setUserPassword(rs.getString("user_password"));
                    userReq.setFirstLastName(rs.getString("user_first_last_name"));
                    userReq.setAboutYourself(rs.getString("user_about_yourself"));
                    userReq.setDateOfRegister(rs.getTimestamp("dateOfRegister"));
                    userReq.setUserRole(rs.getString("user_role"));

                    List<BlogPostRequest> listPost = getPosts(1,10);
                    userReq.setUserPosts(listPost);
                    for(BlogPostRequest bp : listPost) {
                        List<BlogCommentRequest> listComments = getComments((int) bp.getPostId(), 1, 10);
                        userReq.setUserComments(listComments);
                    }
            }
        }
        return userReq;
    }

    public BlogUserResponse deleteUser(BlogUserRequest userName) throws SQLException{
        BlogUserResponse userResp = new BlogUserResponse();
        try(Connection con = getConnect();
        PreparedStatement stmt = con.prepareStatement(DELETE_USER)){
            stmt.setString(1, userName.getUserName());

            int answer = stmt.executeUpdate();

            if(answer > 0){
                userResp.setDeleteUserAnswer(true);
            } else{
                userResp.setDeleteUserAnswer(false);
            }
        }
        return userResp;
    }


    @Override
    public BlogPostRequest getPostWithComments(long postId) throws SQLException {
        BlogPostRequest bp = new BlogPostRequest();
        BlogCommentRequest bc = new BlogCommentRequest();
        List<BlogCommentRequest> commentList = new ArrayList<>();
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

