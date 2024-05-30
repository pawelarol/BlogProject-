package persistance.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistance.Interfaces.DaoPostInterface;
import service.domian.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static persistance.dao.BuilderConnection.getConnection;

public class PostDao implements DaoPostInterface {

    private final static Logger logger = LoggerFactory.getLogger(PostDao.class);


    public Connection getConnect() throws SQLException{
        return getConnection();
    }


    private static final String ADD_POST = "INSERT INTO public.bl_post(" +
            "post_title, post_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?) ";

    private static final String GET_POSTS = "SELECT * FROM public.bl_post" +
            " ORDER BY dateofpublish DESC LIMIT ? OFFSET ? ";

    private static final String DELETE_POST = "DELETE FROM public.bl_post where post_id = ?" ;

    private static Post createRespObject(Post post) {
       Post response = new Post();
        response.setTitle(post.getTitle());
        response.setText(post.getText());
        response.setUser(post.getUser());
        response.setDateOfPublish(post.getDateOfPublish());
        return response;
    }


    @Override
    public Post addPost(Post post) {
       Post postResp = createRespObject(post);

        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_POST, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getText());
            stmt.setString(3, post.getUser().getUserName());
            stmt.setObject(4, post.getDateOfPublish());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        postResp.setPostId(generatedKeys.getLong(1));
                      //  postResp.setStatusPost(ObjectStatus.ADDED);
                    } else {
                        //postResp.setStatusPost(ObjectStatus.ERROR);
                    }
                }
            }

        } catch (SQLException ex) {
             logger.info(String.valueOf(postResp));
            //postResp.setStatusPost(ObjectStatus.ERROR);
        }

        return postResp;
    }



    @Override
    public List<Post> getPosts(int page, int pageSize) throws SQLException {
        List<Post> posts = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_POSTS)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setPostId(rs.getLong("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setText(rs.getString("post_text"));
               //     post.setUserName(rs.getString("publicist_name"));
                    // post.setDateOfPublish(rs.getObject("dateofpublish"));
                    posts.add(post);
                }
            }
        } catch (SQLException ex) {
            //  logger.log(Level.SEVERE, "Error retrieving posts", ex);
        }

        return posts;
    }

    @Override
    public Post getPost(Post post) throws SQLException {
        Post postResp = createRespObject(post);
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(GET_POSTS)) {
            stmt.setLong(1, post.getPostId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                postResp.setPostId(rs.getLong("post_id"));
                postResp.setTitle(rs.getString("post_title"));
                postResp.setText(rs.getString("post_text"));
//                postResp.setStatusPost(rs.getInt("post_status"));
              //  postResp.setUserName(rs.getString("publicist_name"));
                postResp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
            }
            return postResp;
        }
    }

    @Override
    public Post deletePost(Post post) throws SQLException {
        // в этом методе можно будет написать, отправку данных в виде увеличения счетчика
        // для получения статистики по кол-ву удаленных постов
        Post postResp = createRespObject(post);
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(DELETE_POST)) {
            stmt.setLong(1, post.getPostId());
            int ans = stmt.executeUpdate();
            if (ans > 0) {
             //   postResp.setStatusPost(ObjectStatus.DELETED);
            } else {
             //   postResp.setStatusPost(ObjectStatus.ERROR);
            }
        }
        return postResp;
    }
}
