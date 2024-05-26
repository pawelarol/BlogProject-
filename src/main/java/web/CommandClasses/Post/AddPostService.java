package web.CommandClasses.Post;

import persistance.dao.PostDao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("/AddPost")
@Singleton
public class AddPostService {

    // class for Json logic

        private PostDao dao;

        @PostConstruct
        public void init() throws SQLException {
            //    logger.info("SERVICE is created");
            dao = new PostDao();
            dao.getConnect();
        }
//
//        @POST
//        @Consumes(MediaType.APPLICATION_JSON)
//        @Produces(MediaType.APPLICATION_JSON)
//        public Post checkPerson(Post request) throws Exception{
//            //logger.info(request.toString());
//            return dao.addPost(request);
//        }
    }
