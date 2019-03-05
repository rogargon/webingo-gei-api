package cat.udl.eps.entsoftarch.webingogeiapi.repository;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {

  /**
   * Finds the game with given id
   * @param id Integer id
   * @return Returns game object.
   */
  Game findByIdGame(Integer id);
}