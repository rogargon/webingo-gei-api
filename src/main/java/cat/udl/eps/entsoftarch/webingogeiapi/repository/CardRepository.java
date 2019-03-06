package cat.udl.eps.entsoftarch.webingogeiapi.repository;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends PagingAndSortingRepository<Card, String> {

    /**
     * Returns the card related to a game.
     * @param game The Game that contains the cards we want.
     * @return a list of the cards.
     */
    List<Card> findByGame(@Param("game") Game game);
}
