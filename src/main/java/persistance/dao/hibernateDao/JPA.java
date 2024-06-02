package persistance.dao.hibernateDao;

import persistance.Interfaces.DaoHibernateInterface;
import service.domian.Comment;
import service.domian.Post;
import service.domian.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JPA implements DaoHibernateInterface {

    private EntityManagerFactory emf;
    private EntityManager em;


    @Override
    public Long addPostJPA(Post post, User userId) {
        emf = Persistence. createEntityManagerFactory("persistence");
        Long postId = 0L;
        try{
            em = emf.createEntityManager();
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
        Long userId = 0L;
        try{
            em = emf.createEntityManager();
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
    public Long addCommentJPA(Comment comment, Post postId, User userId) {
        Long commentId = 0L;
        emf = Persistence.createEntityManagerFactory("persistence");
        try{
            comment.setPostId(postId);
            comment.setUserId(userId);
            em = emf.createEntityManager();
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
        emf = Persistence.createEntityManagerFactory("persistence");
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Post> query = em.createQuery("SELECT p FROM bl_post p ORDER BY p.post_id", Post.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            posts = query.getResultList();
            em.getTransaction().commit();

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
        emf = Persistence.createEntityManagerFactory("persistence");
        try{
            em = emf.createEntityManager();
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
        emf = Persistence.createEntityManagerFactory("persistence");

        try{
            em = emf.createEntityManager();
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
        emf = Persistence.createEntityManagerFactory("persistence");
        try {
            em = emf.createEntityManager();
            // Неправильно написан hql запрос, я нашел сторонний проект который помогает в генераций запросов
            String hql = "SELECT с FROM bl_comment c  WHERE c.post_id = c.post_id ORDER BY c.date_of_publish DESC";
            TypedQuery<Comment> query = em.createQuery(hql, Comment.class);
            query.setParameter("post_id", postId);
            query.setMaxResults(10);
            comments = query.getResultList();
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
