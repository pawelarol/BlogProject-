package persistance.dao;

import persistance.Interfaces.DaoUserInterface;
import service.domian.User;
import web.CommandClasses.User.BlogUserResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements DaoUserInterface {

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
    public BlogUserResponse addUser(User userBlog) {
        BlogUserResponse response = new BlogUserResponse();
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(ADD_USER)) {

            stmt.setString(1, String.valueOf(userBlog.getUserName()));
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
    public User getUser(User userName) {
        User userReq = new User();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_USER)){
            stmt.setString(1, String.valueOf(userName.getUserName()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
              //  userReq.setUserName(rs.getString("user_name"));
                userReq.setUserMail(rs.getString("user_mail"));
                userReq.setUserPassword(rs.getString("user_password"));
                userReq.setFirstLastName(rs.getString("user_first_last_name"));
                userReq.setAboutYourself(rs.getString("user_about_yourself"));
                userReq.setDateOfRegister(rs.getTimestamp("dateOfRegister"));
                userReq.setUserRole(rs.getString("user_role"));

            }
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userReq;
    }

    public BlogUserResponse deleteUser(User userName){
        BlogUserResponse userResp = new BlogUserResponse();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(DELETE_USER)){
            stmt.setString(1, String.valueOf(userName.getUserName()));

            int answer = stmt.executeUpdate();

            if(answer > 0){
                userResp.setDeleteUserAnswer(true);
            } else{
                userResp.setDeleteUserAnswer(false);
            }
        }

        catch (SQLException ex){
            ex.printStackTrace();
        }
        return userResp;
    }

}
