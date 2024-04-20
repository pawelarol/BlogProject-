package web.Servlets;

import javax.servlet.http.HttpServlet;
import java.net.Socket;

public class UserServlet extends HttpServlet {
  //  private static final Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private static Socket client;

    public void setClient(Socket client) {
        this.client = client;
    }

    public void getBoost() {
        BoosterClass boost = new BoosterClass();
        boost.start();
    }

    static class BoosterClass extends Thread {
        @Override
        public void run() {
   //         logger.info("AddPostServlet is started ");
            addUser();
            deleteUser();
            changeUser();
            settingUser();
        }

        private void settingUser() {
        }

        private void changeUser() {
            
        }

        private void deleteUser() {
        }
        

        private void addUser() {
            
        }

    }
}
