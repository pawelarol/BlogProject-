package service.dao;
import service.domian.BlogComment;
import service.domian.BlogPost;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DictionaryDaoImpl implements DictionaryInterface {

    private DictionaryDaoImpl dao;




    private static final String GET_POSTS = "SELECT post_id, post_title, post_text, dateofpublish " +
            "FROM public.bl_post " +
            "OFFSET ? " +
            "LIMIT ? ";
    private static final String GET_POST = "SELECT post_id, post_title, post_text, dateofpublish " +
            "FROM public.bl_post " +
            "WHERE post_id = ?";

    private static final String ADD_COMMENT = "INSERT INTO public.bl_comment(" +
            "post_id, comment_id, comment_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?, ?) ";
    private static final String ADD_POST = "INSERT INTO public.bl_post(" +
            "post_id, post_title, post_text, publicist_name, dateofpublish )" +
            "VALUES (?, ?, ?, ?, ?) ";
    private static final String GET_COMMENT= "SELECT post_id, comment_id, comment_text, publicist_name, dateofpublish " +
            "FROM public.bl_comment " +
            "OFFSET ? " +
            "LIMIT ? ";

    public Connection getConnect() throws SQLException {
        return BuilderConnection.getConnection();
    }



    public BlogPost getOnePost(long postid) throws SQLException{
        BlogPost bp = new BlogPost();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_POST)){
            stmt.setLong(1,postid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                bp.setPostId(rs.getLong("post_id"));
                bp.setTitle(rs.getString("post_title"));
                bp.setText(rs.getString("post_text"));
                bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
            }
        }
        return bp;
    }

    //ПРосит передать ей параметра для первой колонки

    @Override
    public List<BlogPost> getPosts(int page, int pageSize) {

        int offset = (page - 1) * pageSize;
        List<BlogPost> answer = new ArrayList<>();

        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_POSTS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                    BlogPost bp = new BlogPost();
                    bp.setPostId(rs.getLong("post_id"));
                    bp.setTitle(rs.getString("post_title"));
                    bp.setText(rs.getString("post_text"));
                    bp.setDateOfPublish(rs.getObject("dateOfpublish", LocalDateTime.class));
                    answer.add(bp);
            }

            List<BlogPost> pagePosts = new ArrayList<>();

            for (int i = offset; i < Math.min(offset + pageSize, answer.size()); i++) {
                pagePosts.add(answer.get(i));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        int startIndex = Math.min(offset, answer.size());
        int endIndex = Math.min(offset + pageSize, answer.size());

        return answer.subList(startIndex, endIndex);
    }

    @Override
    public List<BlogComment> getComment(BlogComment commentId) {
        List<BlogComment> answer = new ArrayList<>();
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(GET_COMMENT)){
            stmt.setLong(1, commentId.getPostId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                BlogComment bc = new BlogComment();
                bc.setPostId(rs.getLong(""));
                bc.setText(rs.getString(""));
                bc.setUserName(rs.getString(""));
                bc.setDateOfPublish(rs.getObject("", LocalDateTime.class));
                answer.add(bc);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return answer;
    }

    // сначала нужно получить один пост только нужно определиться в каком классе это должно быть написано

    // в запросе addComment должена быть ссылка на конкретный пост

//    Для добавления комметария к конкретному посту мы должны:
//    1.получить один пост в тестах
//    2.Получить список комментариев для этого поста
//            3. отправить этот список в метод addConmment
//            4.провести заполнение полей

    @Override
    public void addComment(List<BlogComment> blogComments) {
        try(Connection con = getConnect();
        PreparedStatement stmt = con.prepareStatement(ADD_COMMENT)){

            for(BlogComment comment : blogComments) {
                stmt.setLong(1, comment.getCommentId());
                stmt.setString(2, comment.getText());
                stmt.setString(3, comment.getUserName());
                stmt.setObject(4, comment.getDateOfPublish());

                int successCounter = stmt.executeUpdate();
                if (successCounter > 0) {
                    System.out.println("A new comment has been inserted successfully.");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public BlogPost getPostWithComments(long postId) {
      BlogPost bp = new BlogPost();
        List<BlogComment> comments = new ArrayList<>();

        try (Connection con = getConnect();
             PreparedStatement postStmt = con.prepareStatement(GET_POSTS);
             PreparedStatement commentsStmt = con.prepareStatement(GET_COMMENT)) {

            postStmt.setLong(1, postId);
            ResultSet postRs = postStmt.executeQuery();

            if (postRs.next()) {
                bp.setTitle(postRs.getString("post_title"));
                bp.setText(postRs.getString("post_text"));
                bp.setDateOfPublish(postRs.getObject("dateOfpublish", LocalDateTime.class));
            }

            commentsStmt.setLong(1, postId);
            ResultSet commentsRs = commentsStmt.executeQuery();

            while (commentsRs.next()) {
                    BlogComment bc = new BlogComment();
                    bc.setPostId(commentsRs.getLong("comment_id"));
                    bc.setText(commentsRs.getString("comment_text"));
                    bc.setDateOfPublish(commentsRs.getObject("dateOfpublish", LocalDateTime.class));
                    comments.add(bc);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return bp;
    }

    public BlogPost getPostWithComments2(int postId, int page, int pageSize){;
        List<BlogPost> posts = dao.getPosts(page, pageSize);
        BlogPost postForAddComment = posts.get(postId);
        System.out.println(postForAddComment.getTitle() + " " + postForAddComment.getText());
        List<BlogComment> comments1 = postForAddComment.getComments();
        for (BlogComment c : comments1){
            System.out.println(c);
        }
        return postForAddComment;
    }

//    1.Получить данные из тестового класса
//    2.Подклюбчиться к БЗ
//    3.Добавить данные к каждой колонке

    @Override
    public void addPost(BlogPost blogPost) {
        try(Connection con = getConnect();
            PreparedStatement stmt = con.prepareStatement(ADD_POST)){
            stmt.setLong(1,blogPost.getPostId());
            stmt.setString(2,blogPost.getTitle());
            stmt.setString(3,blogPost.getText());
            stmt.setString(4,blogPost.getUserName());
            stmt.setObject(5,blogPost.getDateOfPublish());

            int suсcessCounter = stmt.executeUpdate();
            if (suсcessCounter > 0) {
                System.out.println("A new post has been inserted successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
