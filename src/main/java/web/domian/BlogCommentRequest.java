package web.domian;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

public class BlogCommentRequest  {
    private String postTitle;
    private long commentId;
    private boolean status;
    private String title;
    private String text;
    private String userName;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime dateOfPublish;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public void setPostId(int i) {
    }
}
