package web.CommandClasses.Post;

public class BlogPostResponse {
    private boolean addPostAnswer;
    private boolean deletePostAnswer;
    private boolean editPostAnswer;

    public boolean isAddPostAnswer() {
        return addPostAnswer;
    }

    public void setAddPostAnswer(boolean addPostAnswer) {
        this.addPostAnswer = addPostAnswer;
    }

    public boolean isDeletePostAnswer() {
        return deletePostAnswer;
    }

    public boolean setDeletePostAnswer(boolean deletePostAnswer) {
      return this.deletePostAnswer = deletePostAnswer;
    }

    public boolean isEditPostAnswer() {
        return editPostAnswer;
    }

    public void setEditPostAnswer(boolean editPostAnswer) {
        this.editPostAnswer = editPostAnswer;
    }

    @Override
    public String toString() {
        return "BlogPostResponse{" +
                "addPostAnswer=" + addPostAnswer +
                ", deletePostAnswer=" + deletePostAnswer +
                ", editPostAnswer=" + editPostAnswer +
                '}';
    }
}
