package persistance.dao.hibernateDao;

import persistance.Interfaces.DaoHibernateInterface;
import service.domian.Comment;
import service.domian.Post;
import service.domian.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPA implements DaoHibernateInterface {

    private EntityManagerFactory emf;
    private EntityManager em;

    public JPA(){

    }

    @Override
    public Long addPostJPA(Post post, User userId) {
        emf = Persistence. createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
        Long postId = 0L;
        try{
            em.getTransaction().begin();
            post.setUser(userId);
            em.persist(post);
            em.getTransaction().commit();
            postId = post.getPostId();
            System.out.println("Post ID of the saved post: " + postId);
        } catch (Exception ex){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }


        return postId;
    }

    @Override
    public Long addUserJPA(User user) {
        emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
        Long userId = 0L;
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            userId = user.getUserId();
            System.out.println("User ID of the saved post: " + userId);
        } catch (Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return userId;
    }

    @Override
    public List<Post> getPostsJPA(int page, int pageSize) {
        return null;
    }

    @Override
    public Post getPostJPA(Post postId) {
        return null;
    }

    @Override
    public Long deletePostJPA(Post postId) {
        return null;
    }

    @Override
    public Long addCommentJPA(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getCommentsJPA(int page, int pageSize, Post postId) {
        return null;
    }

    @Override
    public Comment getCommentJPA(Comment commentId) {
        return null;
    }

    @Override
    public Long deleteCommentJPA(Comment commentId) {
        return null;
    }


    @Override
    public User getUserJPA(User userId) {
        return null;
    }

    @Override
    public Long deleteUserJPA(User userId) {
        return null;
    }
}
