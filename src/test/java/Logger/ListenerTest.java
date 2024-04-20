package Logger;

import org.junit.jupiter.api.Test;
import web.listener.Listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;


public class ListenerTest {
    @Test
    public void TestCreated(){
        HttpSession httpSession = new MockHttpSession();
        HttpSessionEvent httpSessionEvent = new HttpSessionEvent(httpSession);

        Listener listener = new Listener();
        listener.sessionCreated(httpSessionEvent);
    }

    @Test
    public void TestDestroyed(){
        HttpSession httpSession = new MockHttpSession();
        HttpSessionEvent httpSessionEvent = new HttpSessionEvent(httpSession);

        Listener listener = new Listener();
        listener.sessionCreated(httpSessionEvent);
    }
}
