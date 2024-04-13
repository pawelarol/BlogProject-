package web.domian;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Random;

public class Client {
    private static final Random random = new Random();

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            BoostClient boost = new BoostClient(i);
            boost.start();
        }
    }


    static class BoostClient extends Thread {

        private final static String[] COMMAND = {"ADD_POST, GET_ONE_POST, GET_POSTS," +
                "GET_POST_WITH_COMMENTS, DELETE_POST, CHANGE_POST, ADD_COMMENT, GET_COMMENTS, " +
                "DELETE_COMMENT, CHANGE_COMMENT, ADD_USER, DELETE_USER, SETTING_USER"};


        private int cmdNumber;

        public BoostClient(int cmdNumber) {
            this.cmdNumber = cmdNumber;
        }

        public void run() {

            try {
                sendRequest();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void sendRequest() throws IOException {
            Socket client = new Socket("127.0.0.1", 2510);

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));


            String command = COMMAND[cmdNumber % COMMAND.length];

            String title = (command + "Title blog" + random.nextInt(1000));
            String text = (command + "Text blog" + random.nextInt(1000));
            String dateOfPublish = command + LocalDateTime.now();
            String userName = (command + "User post" + random.nextInt(1000));

            bw.write(title);
            bw.newLine();
            bw.write(text);
            bw.newLine();
            bw.write(dateOfPublish);
            bw.newLine();
            bw.write(userName);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

        }
    }

}