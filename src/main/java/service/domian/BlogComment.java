package service.domian;

import java.time.LocalDateTime;
import java.util.List;

public class BlogComment extends BlogPost{
    private long postId;
    private long commentId;
    private String text;
    private String userName;
    private LocalDateTime dateOfPublish;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public LocalDateTime getDateOfPublish() {
        return dateOfPublish;
    }

    @Override
    public void setDateOfPublish(LocalDateTime dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }
}
