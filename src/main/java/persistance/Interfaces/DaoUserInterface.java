package persistance.Interfaces;

import service.domian.User;
import web.CommandClasses.User.BlogUserResponse;

public interface DaoUserInterface {
    BlogUserResponse addUser(User userBlog);
    User getUser(User userBlog);
    BlogUserResponse deleteUser(User userName);
}
