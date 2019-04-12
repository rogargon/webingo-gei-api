package cat.udl.eps.entsoftarch.webingogeiapi.repository;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends PagingAndSortingRepository<Player, String> {
  /**
   * Find players with username containing given text.
   * @param text Text that should be included in retrieved players' usernames.
   * @return A list of players.
   */
  List<Player> findByUsernameContaining(@Param("text") String text);

  /**
   * Returns the player related to a card.
   * @param card The Card that contains the player we want.
   * @return a player.
   */
  Optional<Player> findByCard(@Param("card") Card card);

  /**
   * Returns the players related to a game.
   * @param game The game that contains the players we want.
   * @return a list of players.
   */
  List<Player> findByPlayed(@Param("game") Game game);

}
