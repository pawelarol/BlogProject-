package BlogProject.DataBase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dao.DictionaryDaoImpl;
import service.domian.BlogComment;
import service.domian.BlogPost;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandsTest {

    private static final Logger logger = LoggerFactory.getLogger(CommandsTest.class);

    ContentBuilder builder;

     @Test
    public void getPostsTest(){
         logger.info("getPosts are started ");
         DictionaryDaoImpl dao = new DictionaryDaoImpl();
         List<BlogPost> posts = dao.getPosts(1,5);
         for (BlogPost p : posts){
             System.out.println(p.getTitle() + " " + p.getText());
         }

//         последовательность действий для getPostTerst{
//             1.Обратиться к ДАО и вызвать метод GETpost Указав с какого по какой пост он должен вернуть
//             2.Получить результат
//             3.Записать его в коллекцию
//             4.Вывести на экран каждый пост
         }


         @Test
         public void getCommentsTest() {
             logger.info("getComments are started ");
             DictionaryDaoImpl dao = new DictionaryDaoImpl();
             List<BlogPost> posts = dao.getPosts(1,3);
             for (BlogPost p : posts) {
                 System.out.println(p.getTitle() + " " + p.getText());
             }
         }

//    1.Получить данные из создателя постов
//    2.поместить их в список
//            3.Передать их на вход метода addPost
//            4.если данные успешно добавлены вернуть подтверждение
    @Test
    public void addPostsTest(){
         logger.info("AddPosts ara started ");

         builder = new ContentBuilder();
        List<BlogPost> posts = builder.createPost();
        DictionaryDaoImpl dao = new DictionaryDaoImpl();
        for(BlogPost p : posts) {
            dao.addPost(p);
        }
        if(posts != null){
            addPostsTest();
        } else {
            System.out.println("All posts are sent");
        }
    }

    //получить 1 пост
    //Указать к какому посту будут добавляться комментарий
    // добавить комментарий 1
    // Написать получение одого поста поста
    @Test
    public void addCommentTest() throws SQLException {
         logger.info("AddComment is started ");
        DictionaryDaoImpl dao = new DictionaryDaoImpl();
        BlogPost post = dao.getOnePost(1);

        List<BlogComment> commentList = new ArrayList<>();
        dao.addComment(commentList);

        BlogComment bc = new BlogComment();
        bc.setPostId(1);
        bc.setTitle("Title comment ");
        bc.setText("Text comment " );
        bc.setDateOfPublish(LocalDateTime.now());
        bc.setUserName("User comment ");

        commentList.add(bc);
        post.setComments(commentList);

        if(commentList != null){
            addCommentTest();
        }else {
            System.out.println("Comment is added");
        }
    }

//    1.Обратиться к методу из ДАО
//    2.Поместить ответ в коллекцию
//            3.Вывести в консоль результат
//            4.Достать из коллекций данные

    //    Для добавления комметария к конкретному посту мы должны:
    // при открытий приложение он получает список постов
    // далее он выбирает один из постов
    // получает пост и список комментариев к нему
    // добавляет комментарий

    //    1.получить один пост в тестах
//    2.Получить список комментариев для этого поста
//            3. отправить этот список в метод addConmment
//            4.провести заполнение полей
    @Test
    public void getPostWithCommentsTest(){
         logger.info("Get post W Comments is started");
         DictionaryDaoImpl dao = new DictionaryDaoImpl();
        List<BlogPost> posts = dao.getPosts(1, 3);
        BlogPost postForAddComment = posts.get(2);
        List<BlogComment> comments1 = postForAddComment.getComments();
            for (BlogComment c : comments1){
                System.out.println(c);
            }
        }


        // ??? Вопрос для постАЙДИ лучше ставить лонг, но работать удобнее с интом
        @Test
        public void getPostWithCommentsTest2(){
            logger.info("Get post W Comments2 is started");
            DictionaryDaoImpl dao = new DictionaryDaoImpl();
            dao.getPostWithComments2(2,1,3);
        }
}

