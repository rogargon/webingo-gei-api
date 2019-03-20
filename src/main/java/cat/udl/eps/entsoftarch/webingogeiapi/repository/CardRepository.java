package cat.udl.eps.entsoftarch.webingogeiapi.repository;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource


public interface CardRepository extends PagingAndSortingRepository<Card, Integer> {

    /**
     * Returns the card related to a game.
     * @param game The Game that contains the cards we want.
     * @return a list of the cards.
     */
    List<Card> findByGame(@Param("game") Game game);
}
