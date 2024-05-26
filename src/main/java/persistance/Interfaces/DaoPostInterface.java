package persistance.Interfaces;

import service.domian.Post;
import java.sql.SQLException;
import java.util.List;

public interface DaoPostInterface {
  Post addPost(Post post) throws SQLException;
  List<Post> getPosts(int page, int pageSize) throws SQLException;
  Post getPost(Post post ) throws SQLException;
  Post deletePost(Post post) throws SQLException;

}
