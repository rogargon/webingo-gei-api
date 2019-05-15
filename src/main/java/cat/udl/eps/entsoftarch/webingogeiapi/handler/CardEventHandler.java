package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.exception.UserAlreadyJoinedException;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RepositoryEventHandler

public class CardEventHandler {
    final Logger logger = LoggerFactory.getLogger(Card.class);

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PlayerRepository playerRepository;

    @HandleBeforeCreate
    public void handleCardBeforeCreate(Card card) throws UserAlreadyJoinedException {
        List<Player> list = playerRepository.findByPlayed(card.getGame());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Player actualPlayer = null;
        if(authentication.getPrincipal() instanceof Player) actualPlayer = (Player) authentication.getPrincipal();
        if(!list.isEmpty()){
            for(Player p : list)
                if(p.getId().equals(actualPlayer.getId()))
                    throw new UserAlreadyJoinedException();
        }
        card.generateCard();
        cardRepository.save(card);

        if (actualPlayer != null){
            actualPlayer.setCard(card);
            playerRepository.save(actualPlayer);
        }
    }

    @HandleBeforeDelete
    @Transactional
    public void handleInvitationPreDelete(Card card) throws Throwable{
        if (!playerRepository.findByCard(card).isEmpty()){
            Player player = (Player) playerRepository.findByCard(card).get(0);
            player.setCard(null);
        }
    }
}
