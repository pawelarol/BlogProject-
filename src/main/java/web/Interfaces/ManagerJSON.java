package web.Interfaces;

import service.domian.BlogComment;
import service.domian.BlogPost;
import java.time.LocalDateTime;
import java.util.List;

public abstract class ManagerJSON {

    // врмененаая реализация для одной строки
    // нужно определить что передать в этот метод
    // ри использований такого метода развилки для трех функциональностей необхзодимо
    // добавлять новый абстрактный класс, либо же использовать rest or JSON


    public abstract String addPost();
    public String buildResponse(String command, String userName){
        return null;
    }

    private static final  int startCounter = 1;
    private static final int finishCounter = 3;
    private int limit = finishCounter - startCounter + 1;
    private int offset = startCounter - 1;
    private long postId;
    private boolean status;
    private String title;
    private String text;
    private String userName;
    private LocalDateTime dateOfPublish;
    private List<BlogPost> listPost;
    private List<BlogComment> comments;

}
