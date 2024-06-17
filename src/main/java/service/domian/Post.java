package service.domian;

import web.domian.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "bl_post", uniqueConstraints = {
        @UniqueConstraint(columnNames = "post_id"),
        @UniqueConstraint(columnNames = "post_text"),
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "date_of_publish")
})
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "post_id", unique = true, nullable = false)
    private Long postId;
    @Transient
    private ObjectStatus statusPost;
    @Column(name = "post_title", length = 255)
    private String title;
    @Column(name = "post_text", unique = true, nullable = false, length = 25000)
    private String text;
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "date_of_publish", unique = true, nullable = false)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime dateOfPublish;
    @Transient
    // ДОБАВИТЬ ПОСЛЕ ПРОВЕРКИ КЛЮЧЕЙ ВНУТРИ БАЗ И ВЫСТАВЛЕНИЯ ПРАВИЛЬНЫХ ЗАВИСИМОСТЕЙ
    // МЕЖДУ СУЩНОСТЯМИ
    private List<Comment> postComments;

    public Post() {

    }

    public Post(Long postId, String text, User user) {
        this.postId = postId;
        this.text = text;
        this.user = user;
    }

    public Post(Long postId, ObjectStatus statusPost, String title, String text, User user, LocalDateTime dateOfPublish, List<Comment> postComments) {
        this.postId = postId;
        this.statusPost = statusPost;
        this.title = title;
        this.text = text;
        this.user = user;
        this.dateOfPublish = dateOfPublish;
        this.postComments = postComments;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public List<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comment> postComments) {
        this.postComments = postComments;
    }


    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Transient
    public ObjectStatus getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(ObjectStatus statusPost) {
        this.statusPost = statusPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDateTime dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", statusPost=" + statusPost +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", userName='" + user + '\'' +
                ", dateOfPublish=" + dateOfPublish +
                '}';
    }
}