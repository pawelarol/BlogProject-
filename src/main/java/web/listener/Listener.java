package web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Listener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);
    private static FileHandler fh;

    static {
        try {
            fh = new FileHandler("A:\\javaCode\\Git\\BlogProject\\src\\main\\resources\\UserSessions.log");
            logger.info(fh.toString());
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

          logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Session has been created " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session has been destroyed " + se.getSession().getId());
    }


}
