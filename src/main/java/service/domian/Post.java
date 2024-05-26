package service.domian;

import web.domian.LocalDateAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;



public class Post {

    private long postId;
    private int statusPost;
    private String title;
    private String text;
    private String userName;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime dateOfPublish;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public int getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(int statusPost) {
        this.statusPost = statusPost;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                ", dateOfPublish=" + dateOfPublish +
                '}';
    }
}