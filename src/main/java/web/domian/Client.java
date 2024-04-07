package web.domian;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Random;

public class Client {

    private static final String[] COMMAND = {
            "ADDPOST"
    };
    public static void main(String[] args) throws IOException {
        sendRequest();
    }

    private static final Random random = new Random();
    private static void sendRequest() throws IOException {
        Socket client = new Socket("127.0.0.1", 2510);

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String title = ("Title blog" + random.nextInt(1000));
        String text = ("Text blog" + random.nextInt(1000));
        String dateOfPublish = String.valueOf((LocalDateTime.now()));
        String userName = ("User post" + random.nextInt(1000));
        bw.write(title);
        bw.newLine();
        bw.write(text);
        bw.newLine();
        bw.write(dateOfPublish);
        bw.newLine();
        bw.write(userName);
        bw.newLine();
        bw.flush();

        //здесь необходимо понять в каком классе и мотоде мы сможем написать возврат
        // в виде строки для клиента
        //String answerServer = br.readLine();
        //System.out.println(answerServer);

        br.close();
        bw.close();

    }
}
