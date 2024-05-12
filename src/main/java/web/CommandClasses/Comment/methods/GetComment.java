package web.CommandClasses.Comment.methods;

import web.Interfaces.ManagerResponse;

public class GetComment extends ManagerResponse {
    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "GETCOMMENT") {
                ans = "BuildRespons GetComment is created";
            } else {
                ans = "Not for this class GetComment";
            }
            return ans;
        }
    }

