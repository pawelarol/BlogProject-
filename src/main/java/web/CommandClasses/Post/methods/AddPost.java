package web.CommandClasses.Post.methods;

import web.Interfaces.ManagerResponse;

public class AddPost extends ManagerResponse {
    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "ADDPOST") {
                ans = "BuildRespons ADDPost is created";
            } else {
                ans = "Not for this class AddPost";
            }
            return ans;
        }
    }