package web.CommandClasses.Comment;

public class BlogCommentResponse {
    private boolean addComAns;
    private boolean delComAns;
    private boolean editComAns;

    public boolean isAddComAns() {
        return addComAns;
    }

    public boolean setAddComAns(boolean addComAns) {
        this.addComAns = addComAns;
        return addComAns;
    }

    public boolean isDelComAns() {
        return delComAns;
    }

    public void setDelComAns(boolean delComAns) {
        this.delComAns = delComAns;
    }

    public boolean isEditComAns() {
        return editComAns;
    }

    public void setEditComAns(boolean editComAns) {
        this.editComAns = editComAns;
    }
}
