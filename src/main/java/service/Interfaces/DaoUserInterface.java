package service.Interfaces;

import service.domian.BlogUserRequest;
import web.CommandClasses.User.BlogUserResponse;

public interface DaoUserInterface {
    BlogUserResponse addUser(BlogUserRequest userBlog);
    BlogUserRequest getUser(BlogUserRequest userBlog);
    BlogUserResponse deleteUser(BlogUserRequest userName);
}
