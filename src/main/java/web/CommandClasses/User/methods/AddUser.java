package web.CommandClasses.User.methods;

import web.Interfaces.ManagerResponse;

public class AddUser extends ManagerResponse {
    @Override
    public String buildResponse(String command, String postTitle, String commentTitle, String userName) {
            String ans = null;
            if (command == "ADDUSER") {
                ans = "BuildRespons ADDUSER is created";
            } else {
                ans = "Not for this class AddUSER";
            }
            return ans;
        }
    }

