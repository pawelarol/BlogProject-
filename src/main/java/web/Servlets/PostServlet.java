package web.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import service.domian.BlogPost;
import web.Interfaces.Manager;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

// для управления командами мы здесь установим аннотацию с название ссылки
public class PostServlet extends Manager {

    private static final Logger logger = LoggerFactory.getLogger(PostServlet.class);
    private static Socket client;

    private Map<String, Manager> handlers;

    public PostServlet(Map<String, Manager> handlers) {
        this.handlers = handlers;
    }

    public void setClient(Socket client) {
        this.client = client;
    }


    @Override
    public String addInf(String command) {
        String answer = null;
        BlogPost blogPost = createObjectPost();
        logger.info("Add post is started");
        Manager handler = handlers.get(command);
        if (handler != null) {
            boolean bp = new DictionaryDaoImpl().addPost(blogPost);
            if (bp == true) {
                answer = ("New post is added!");
            } else {
                addInf(command);
            }
        } else{
            answer = ("method addPost is not started");
        }
        return answer;
    }


        //в этом методе будет реализован функционал запроса клиента с бизнесом и БЗ
        private BlogPost createObjectPost() {
            BlogPost bp = new BlogPost();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String post = br.readLine();
                String[] lines = post.split("\\s+");

                bp.setTitle(lines[0]);
                bp.setText(lines[1]);
                bp.setDateOfPublish(LocalDateTime.parse(lines[2]));
                bp.setUserName(lines[3]);

                // здесь должны приходить данные и перенаправляться в бизнес логику

                bw.newLine();
                bw.flush();

                br.close();
                bw.close();

                client.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
            return bp;
        }
    }

