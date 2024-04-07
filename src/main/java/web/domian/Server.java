package web.domian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.Interfaces.Manager;
import web.Servlets.CommentServlet;
import web.Servlets.UserServlet;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static Socket client;

    public Server(Socket client) {
        this.client = client;
    }

    // здесь была сделана реализация передачи клиента не через конструкторы, а через сетеры
    // с одним конструктором на сервере


    //server start
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(2510, 1000);
        while (true) {
            System.out.println("Server is started");
            client = socket.accept();
            handleRequest(client);

        }
    }

            private static handleRequest(String command, Socket client) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                    String clientText = br.readLine();


                    // здесь должны приходить данные и перенаправляться в бизнес логику


                    bw.write(sb.toString());
                    bw.newLine();
                    bw.flush();

                    br.close();
                    bw.close();

                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }

                Map<String, Manager> postList = BoosterClass.postImpl();

                BoosterClass boost = new BoosterClass();
                boost.start();
            }
        }

    static class BoosterClass extends Thread {
        @Override
        public void run() {
            logger.info("method run is started ");
            postImpl();
            commentImpl();
            userImpl();
        }

        private static void commentImpl() {
            CommentServlet commentServlet = new CommentServlet();
            commentServlet.setClient(client);
            commentServlet.getBoost();
        }

        private static Map<String, Manager> postImpl() {
            logger.info("AddPostServlet is started ");
            Map<String, Manager> postList = new HashMap<>();
            try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")) {

                Properties properties = new Properties();
                properties.load(is);

                for (Object command : properties.keySet()) {
                    String className = properties.getProperty(command.toString());
                    Class<Manager> cl = (Class<Manager>) Class.forName(className);

                    Manager handler = cl.getConstructor().newInstance();
                    postList.put(command.toString(), handler);
                }
            } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return postList;
        }

        private static void userImpl() {
            UserServlet userServlet = new UserServlet();
            userServlet.setClient(client);
            userServlet.getBoost();
        }

        // in this method I created преобразователь байтов
        //необходимо сделать разветвитель который будет понимать
        //какую операцию пользователь хочет выполнить возможно это лучше всего будет реализовать через
        // switch case

    }
}
