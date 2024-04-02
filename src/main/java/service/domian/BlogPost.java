package service.domian;

import java.time.LocalDateTime;
import java.util.List;

public class BlogPost {

    private static final  int startCounter = 1;
    private static final int finishCounter = 3;
    private int limit = finishCounter - startCounter + 1;
    private int offset = startCounter - 1;
    private long postId;
    private boolean status;
    private String title;
    private String text;
    private String userName;
    private LocalDateTime dateOfPublish;
    private List<BlogPost> listPost;
    private List<BlogComment> comments;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStartCounter() {
        return startCounter;
    }

    public int getFinishCounter() {
        return finishCounter;
    }


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

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
