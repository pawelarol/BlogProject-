package service.domian;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

// добавить в версию 1.1 функциональность "показать комментарий
// и посты пользователя" нужна ли нам коллекция в структуре user
// private List<BlogCommentRequest> userComments;

@Entity
@Table( name = "bl_user")
public class User implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
//@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
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

@Transient // временно
@Column(name = "user_avatar")
    private byte[] userAvatar;

@Column (name = "date_of_register", nullable = false)
    private Timestamp dateOfRegister;

    @Column (name = "user_role", nullable = false)
    private String userRole;


    public User() {
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", aboutYourself='" + aboutYourself + '\'' +
                ", userAvatar=" + Arrays.toString(userAvatar) +
                ", dateOfRegister=" + dateOfRegister +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
