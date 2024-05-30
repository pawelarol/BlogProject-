package web.domian;

import java.sql.Timestamp;
import java.util.List;

public class UserRequest {
    private long userId;
    private String userName;
    private String userMail;
    private String userPassword;
    private String firstLastName;
    private String aboutYourself;
    private byte[] userAvatar;
    private Timestamp dateOfRegister;
    private List<BlogCommentRequest> userComments;
    private String userRole;


    public Timestamp getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Timestamp dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
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
