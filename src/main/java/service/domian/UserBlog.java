package service.domian;

import java.time.LocalDateTime;
import java.util.List;

public class UserBlog {
    private long userId;
    private String userName;
    private String userMail;
    private String password;
    private String flName;
    private byte[] userAvatar;
    private LocalDateTime dateOfRegister;
    private List<BlogPostRequest> userPosts;
    private List<BlogCommentRequest> userComments;
    private String userRole;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public LocalDateTime getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(LocalDateTime dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public List<BlogPostRequest> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<BlogPostRequest> userPosts) {
        this.userPosts = userPosts;
    }

    public List<BlogCommentRequest> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<BlogCommentRequest> userComments) {
        this.userComments = userComments;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}

