package web.CommandClasses.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import service.domian.BlogPostRequest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/check")
@Singleton
public class AddPostService {

    // class for Json logic

        private static final Logger logger = LoggerFactory.getLogger(AddPostService.class);

        private DictionaryDaoImpl dao;

        @PostConstruct
        public void init() throws SQLException {
            logger.info("SERVICE is created");
            dao = new DictionaryDaoImpl();
            dao.getConnect();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public BlogPostResponse checkPerson(BlogPostRequest request) throws Exception{
            logger.info(request.toString());
            return dao.addPost(request);
        }
    }
