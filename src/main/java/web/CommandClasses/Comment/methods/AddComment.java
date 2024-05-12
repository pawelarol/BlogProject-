package web.CommandClasses.Comment.methods;

import web.Interfaces.ManagerResponse;

public class AddComment extends ManagerResponse {

    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "ADDCOMMENT") {
                ans = "BuildRespons ADDComment is created";
            } else {
                ans = "Not for this class AddComment";
            }
            return ans;
        }
    }
