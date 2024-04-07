package web.Servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.Interfaces.Manager;

import java.net.Socket;

public class CommentServlet extends Manager {

    private static final Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private static Socket client;

    public void setClient(Socket client) {
        this.client = client;
    }

    public void getBoost() {
        BoosterClass boost = new BoosterClass();
        boost.start();
    }

    @Override
    public String addInf(String command) {
        return null;
    }

    static class BoosterClass extends Thread {
        @Override
        public void run() {
            logger.info("AddPostServlet is started ");
            addComment(client);
            getComments();
            deleteComment();
            changeComment();
        }

        private void getComments() {
        }

        private void changeComment() {
        }

        private void deleteComment() {
            
        }

        private void addComment(Socket client) {
            
        }

        }
    }
    
