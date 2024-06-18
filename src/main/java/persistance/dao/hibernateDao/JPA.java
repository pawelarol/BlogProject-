package persistance.dao.hibernateDao;

import persistance.Interfaces.DaoHibernateInterface;
import service.domian.Comment;
import service.domian.Post;
import service.domian.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JPA implements DaoHibernateInterface {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    private EntityManager em;


    @Override
    public Long addPostJPA(Post post, User userId) {
        Long postId = 0L;
        try{
            em = getEntityManagerFactory().createEntityManager();
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
        long userId = 0L;
        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            User userAnswer = em.find(User.class, user.getUserId());
            userId = userAnswer.getUserId();
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
    public Long addCommentJPA(Comment comment, Post postId, User userId) {
        Long commentId = 0L;
        try{
<<<<<<< HEAD
            comment.setPost(postId);
            comment.setUser(userId);
            em = emf.createEntityManager();
=======
            comment.setPostId(postId);
            comment.setUserId(userId);
            em = getEntityManagerFactory().createEntityManager();
>>>>>>> temp-branch
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
            commentId = comment.getCommentId();
            System.out.println("Comment ID of the saved post: " + commentId);
        } catch (Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return commentId;
    }

    @Override
    public List<Post> getPostsJPA(int page) {
        final int pageSize = 10;
        List<Post> posts = new ArrayList<>();
        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

           Query query = em.createNamedQuery("Post.getPosts");
           query.setParameter("p.userId" , 1L );
            posts = query.getResultList();
//            TypedQuery<Post> query = em.createQuery("SELECT p FROM bl_post p ORDER BY p.post_id", Post.class);
//            query.setFirstResult((page - 1) * pageSize);
//            query.setMaxResults(pageSize);
//            posts = query.getResultList();
//            em.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }

        return posts;
    }

    @Override
    public Post getPostJPA(Long postId) {
        Post post = null;
        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            post = em.find(Post.class,postId);
            List<Comment> commentsJPA = getCommentsJPA(postId);
            post.setPostComments(commentsJPA);

            em.getTransaction().commit();
        } catch (Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return post;
    }

    @Override
    public Long deletePostJPA(Post post) {
        long answer = 0L;
        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            Post findPost = em.find(Post.class, post.getPostId());
            if (findPost != null) {
                em.remove(findPost);
            }
            em.getTransaction().commit();
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            } else {
                answer = post.getPostId();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }
        return answer;
    }


    public List<Comment> getCommentsJPA(long postId) {
        EntityManager em = null;
        List<Comment> comments = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            // Неправильно написан hql запрос, я нашел сторонний проект который помогает в генераций запросов

            comments = em.createQuery("SELECT c FROM bl_comment c ").getResultList();
//            String hql = "SELECT с FROM bl_comment c  WHERE c.post_id = c.post_id ORDER BY c.date_of_publish DESC";
//            TypedQuery<Comment> query = em.createQuery(hql, Comment.class);
//            query.setParameter("post_id", postId);
//            query.setMaxResults(10);
//            comments = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comments;
    }


    @Override
    public Comment getCommentJPA(Comment commentId, long postId) {
        Comment comment = null;
        emf = Persistence.createEntityManagerFactory("persistence");

        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            String jpql = "SELECT c FROM bl_comment c WHERE c.post_id = :post_id AND c.comment_id = :comment_id";
            comment = em.createQuery(jpql, Comment.class)
                    .setParameter("postId", postId)
                    .setParameter("commentId", commentId)
                    .getSingleResult();
            em.getTransaction().commit();
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return comment;
    }

    @Override
    public Long deleteCommentJPA(Comment commentId) {
        return null;
    }


    @Override
    public User getUserJPA(long userId) {
        User user = null;

        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            user = em.find(User.class, userId);
            em.getTransaction().commit();

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return user;
    }

    @Override
    public User deleteUserJPA(User user) {
        User userAnswer = null;

        try{
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            em.getTransaction().commit();

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            } else{
                userAnswer = em.find(User.class, user.getUserId());
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }

        finally {
            em.close();
        }
        return userAnswer;
    }
}
