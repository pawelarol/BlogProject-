package web.domian;

public class WebPage {
    private BlogUserRequest userName;
    private PostResponse posts;
    private BlogCommentRequest comments;

    public BlogUserRequest getUserName() {
        return userName;
    }

    public void setUserName(BlogUserRequest userName) {
        this.userName = userName;
    }

    public PostResponse getPosts() {
        return posts;
    }

    public void setPosts(PostResponse posts) {
        this.posts = posts;
    }

    public BlogCommentRequest getComments() {
        return comments;
    }

    public void setComments(BlogCommentRequest comments) {
        this.comments = comments;
    }
}
