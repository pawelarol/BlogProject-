package web.domian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import web.Interfaces.ManagerJSON;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

    // здесь была сделана реализация передачи клиента не через конструкторы, а через сетеры
    // с одним конструктором на сервере

    private static final Logger logger = LoggerFactory.getLogger(Server.class);


    //server start
    public static void main(String[] args) throws IOException {

        Map<String,ManagerJSON> handlers = loadHaldlers();

        ServerSocket socket = new ServerSocket(2510, 1000);
        while (true) {
            System.out.println("Server is started");
            Socket client = socket.accept();
            new BoosterClass(client,handlers).start();

        }
    }

    private static Map<String, ManagerJSON> loadHaldlers() {
        logger.info("AddPostServlet is started ");
        Map<String, ManagerJSON> postList = new HashMap<>();
        try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")) {

            Properties properties = new Properties();
            properties.load(is);

            for (Object command : properties.keySet()) {
                String className = properties.getProperty(command.toString());
                Class<ManagerJSON> cl = (Class<ManagerJSON>) Class.forName(className);

                ManagerJSON handler = cl.getConstructor().newInstance();
                postList.put(command.toString(), handler);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return postList;
    }
}

       class BoosterClass extends Thread {

           private static final Logger logger = LoggerFactory.getLogger(Server.class);

           public static Socket client;
           private Map<String, ManagerJSON> handlers;
           public DictionaryDaoImpl dao;

           public BoosterClass(Socket client, Map<String, ManagerJSON> handlers) {
               this.client = client;
           }

           @Override
           public void run() {
               logger.info("method run is started ");
               handleRequest();

           }

           private void handleRequest() {
               try {
                   BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                   String post = br.readLine();
                   String[] lines = post.split("\\s+");

                   // дописмать реализацию создания обьекта

                   String command = lines[0];
                   String title = lines[1];
                   String text = lines[2];
                   LocalDateTime dateOfPublish = LocalDateTime.parse(lines[3]);
                   String userName = lines[4];

                   String response = buildResponse(command, userName);

                   bw.newLine();
                   bw.flush();

                   br.close();
                   bw.close();

               } catch (IOException ex) {
                   ex.printStackTrace(System.out);
               }
           }

           private String buildResponse(String command, String userName) {
               ManagerJSON handler = handlers.get(command);
               if (handler != null) {
                   return handler.buildResponse(command, userName);
               }
               return "Hello, " + userName;
           }
       }



        // in this method I created преобразователь байтов
        //необходимо сделать разветвитель который будет понимать
        //какую операцию пользователь хочет выполнить возможно это лучше всего будет реализовать через
        // switch case
