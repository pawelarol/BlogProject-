package web.domian;

import web.Interfaces.ManagerResponse;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

        //  у меня не обрабатываются команды
        //  у меня не обрабатываются команды
//    мне необходимо подавать список на метод который будет проходиться по всем классам и в самих
//    классах написать проверку команды для если же команда не подходит то метод не начинается
//            если же подходит то метод пошел и не распространяется на другие

        //ПЛАН
        // Есть команды и данные которые должны быть переданы с ними
        // После того кк обработчик прочитал тип коменды мы должны создать экземпляр класса
        // и поместить его в МАР
        // далее этот МАР отправляется в managerResponse
        // и оттуда уже вызывается необходимый нам метод
        // а если мы хотим добавить функционал то нам необходимо будет создать только новый класс с ответом
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(2510, 1000);

            Map<String, ManagerResponse> handlers = loadHendlers();
            System.out.println("Server is created");
            while (true) {
                Socket client = socket.accept();
                new SimpleServer(client, handlers);
            }
        }



        private static Map<String, ManagerResponse> loadHendlers() {
            Map<String, ManagerResponse> result = new HashMap<>();

            try (InputStream is = Server.class.getClassLoader().getResourceAsStream("/server.properties")) {

                Properties properties = new Properties();
                if (is != null) {
                    properties.load(is);
                    System.out.println("Properties are founded");

                    for (Object command : properties.keySet()) {
                        String className = properties.getProperty(command.toString());
                        Class<ManagerResponse> cl = (Class<ManagerResponse>) Class.forName(className);

                        ManagerResponse handler = cl.getConstructor().newInstance();
                        result.put(command.toString(), handler);
                    }
                }  else {
                    System.out.println("Properties have not found");

                }
            } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return result;
        }
    }


    class SimpleServer extends Thread {

        private Socket client;
        private Map<String, ManagerResponse> handlers;

        public SimpleServer(Socket client, Map<String, ManagerResponse> handlers) {
            this.client = client;
        }

        public void run() {
            handleRequest();
        }

        private void handleRequest() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String request = br.readLine();
                String[] lines = request.split("\\s+");

                String command = lines[0];
                String postTitle = lines[1];
                String commentTitle = lines[2];
                String userName = lines[3];

                // после того как мы получили комманду должны понять какой метод
                // вызывать: пост, коммент, или же пользователь

                String response = buildResponseServer(command, postTitle, commentTitle, userName);
                bw.write(response);
                bw.newLine();
                bw.flush();

                br.close();
                bw.close();

                client.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        private String buildResponseServer(String command, String postTitle, String commentTitle, String userName) {
            ManagerResponse handler = handlers.get(command);
            if (handler != null) {
                return handler.buildResponse(command, postTitle, commentTitle, userName);
            }
            return "Hello from server, " + userName;
        }


    }

