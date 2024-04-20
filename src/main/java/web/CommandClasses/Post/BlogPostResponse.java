package web.CommandClasses.Post;

public class BlogPostResponse {
    private boolean addPostAnswer;


    @Override
    public String toString() {
        return "BlogPostResponse{" +
                "addPostAnswer=" + addPostAnswer +
                '}';
    }

    public boolean isAddPostAnswer() {
        return addPostAnswer;
    }

    public void setAddPostAnswer(boolean addPostAnswer) {
        this.addPostAnswer = addPostAnswer;
    }
}
