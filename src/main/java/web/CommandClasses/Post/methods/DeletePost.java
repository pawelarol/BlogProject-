package web.CommandClasses.Post.methods;

import web.Interfaces.ManagerResponse;

public class DeletePost extends ManagerResponse {
    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "DELETEPOST") {
                ans = "BuildRespons DELETEPost is created";
            } else {
                ans = "Not for this class DELETEPost";
            }
            return ans;
        }
    }