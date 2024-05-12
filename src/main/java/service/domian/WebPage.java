package service.domian;

public class WebPage {
    private BlogUserRequest userName;
    private BlogPostRequest posts;
    private BlogCommentRequest comments;

    public BlogUserRequest getUserName() {
        return userName;
    }

    public void setUserName(BlogUserRequest userName) {
        this.userName = userName;
    }

    public BlogPostRequest getPosts() {
        return posts;
    }

    public void setPosts(BlogPostRequest posts) {
        this.posts = posts;
    }

    public BlogCommentRequest getComments() {
        return comments;
    }

    public void setComments(BlogCommentRequest comments) {
        this.comments = comments;
    }
}
