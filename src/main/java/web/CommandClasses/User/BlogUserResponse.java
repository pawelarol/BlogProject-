package web.CommandClasses.User;

public class BlogUserResponse {
        private boolean addUserAnswer;
        private boolean deleteUserAnswer;

    public boolean isAddUserAnswer() {
        return addUserAnswer;
    }

    public void setAddUserAnswer(boolean addUserAnswer) {
        this.addUserAnswer = addUserAnswer;
    }

    public boolean isDeleteUserAnswer() {
        return deleteUserAnswer;
    }

    public void setDeleteUserAnswer(boolean deleteUserAnswer) {
        this.deleteUserAnswer = deleteUserAnswer;
    }


    @Override
    public String toString() {
        return "UserResponse{" +
                "addUserAnswer=" + addUserAnswer +
                ", deleteUserAnswer=" + deleteUserAnswer +
                '}';
    }
}
