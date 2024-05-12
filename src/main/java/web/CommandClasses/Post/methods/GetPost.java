package web.CommandClasses.Post.methods;

import web.Interfaces.ManagerResponse;

public class GetPost extends ManagerResponse {
    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "GETPOST") {
                ans = "BuildRespons GETPost is created";
            } else {
                ans = "Not for this class GETPost";
            }
            return ans;
        }
    }

