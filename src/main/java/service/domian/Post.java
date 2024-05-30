package service.domian;

import web.domian.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "bl_post")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class Post implements Serializable {

    public Post() {

    }

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "post_id")
    private Long postId;
    @Transient
    private ObjectStatus statusPost;
    @Column(name = "post_title")
    private String title;
    @Column(name = "post_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date_of_publish")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime dateOfPublish;

    public long getPostId() {
        return postId;
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