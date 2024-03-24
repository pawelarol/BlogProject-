package service.domian;

import java.time.LocalDateTime;
import java.util.List;

public class BlogPost {

    private List<BlogPost> listPost;
    private long postId;
    private String title;
    private String text;
    private String userName;
    private LocalDateTime dateOfPublish;
    private List<BlogComment> comments;


    public List<BlogPost> getListPost() {
        return listPost;
    }

    public void setListPost(List<BlogPost> listPost) {
        this.listPost = listPost;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<BlogComment> getComments() {
        return comments;
    }

    public void setComments(List<BlogComment> comments) {
        this.comments = comments;
    }
}
