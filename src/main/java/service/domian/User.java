package service.domian;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table( name = "bl_user")
public class User implements Serializable {

    public User(){

    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
    private Long userId;
@Column(name = "user_name", nullable = false)
    private String userName;
@Column(name = "user_mail", nullable = false)
    private String userMail;
@Column(name = "user_password", nullable = false)
    private String userPassword;
@Column(name = "user_first_last_name")
    private String firstLastName;
@Column(name = "user_about_yourself")
    private String aboutYourself;
@Column(name = "user_avatar")
    private byte[] userAvatar;
@Column (name = "date_of_register", nullable = false)
    private Timestamp dateOfRegister;

    //  если мы не пишем функциональность "показать комментарий
    // и посты пользователя" нужна ли нам коллекция в структуре user
   // private List<BlogCommentRequest> userComments;

    @Column (name = "user_role", nullable = false)
    private String userRole;

  //  public List<BlogCommentRequest> getUserComments() {
//        return userComments;
//    }
//
//    public void setUserComments(List<BlogCommentRequest> userComments) {
//        this.userComments = userComments;
//    }

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

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Timestamp getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Timestamp dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }


    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
