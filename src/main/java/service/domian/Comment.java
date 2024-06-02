package service.domian;

import web.domian.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bl_comment")
public class Comment implements Serializable {

    public Comment(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;
    @Column(name = "comment_title")
    private String title;
    @Column(name = "comment_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "date_of_publish")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime dateOfPublish;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDateTime dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", dateOfPublish=" + dateOfPublish +
                '}';
    }
}
