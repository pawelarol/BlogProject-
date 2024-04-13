package web.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import service.domian.BlogPost;
import web.Interfaces.ManagerJSON;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

// для управления командами мы здесь установим аннотацию с название ссылки
public class PostServlet {

    private static final Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private static Socket client;

    private Map<String, ManagerJSON> handlers;

    public PostServlet(Map<String, ManagerJSON> handlers) {
        this.handlers = handlers;
    }

    public void setClient(Socket client) {
        this.client = client;
    }


}

