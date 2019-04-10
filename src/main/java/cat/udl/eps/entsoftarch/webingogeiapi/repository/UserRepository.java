package cat.udl.eps.entsoftarch.webingogeiapi.repository;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, String> {

  /**
   * Finds the user with given email
   * @param email String email
   * @return Returns user object.
   */
  User findByEmail(String email);
  List<User> findByUsernameContaining(@Param("text") String text);
}
