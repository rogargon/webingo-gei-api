package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.PlayerRepository;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class PlayerEventHandler {
    final Logger logger = LoggerFactory.getLogger(Player.class);

    @Autowired
    PlayerRepository playerRepository;

    @HandleBeforeCreate
    @Transactional
    public void handlePlayerPreCreate(Player player) {
        logger.info("Before creating: {}", player.toString());
    }

    @HandleBeforeSave
    @Transactional
    public void handlePlayerPreSave(Player player){
        logger.info("Before updating: {}", player.toString());
    }

    @HandleBeforeDelete
    @Transactional
    public void handlePlayerPreDelete(Player player){
        logger.info("Before deleting: {}", player.toString());
    }

    @HandleBeforeLinkSave
    @Transactional
    public void handlePlayerPreLinkSave(Player player, Object o) {
        logger.info("Before linking: {} to {}", player.toString(), o.toString());
    }

    @HandleAfterCreate
    @Transactional
    public void handlePlayerPostCreate(Player player){
        logger.info("After creating: {}", player.toString());
        player.encodePassword();
        playerRepository.save(player);
    }

    @HandleAfterSave
    @Transactional
    public void handlePlayerPostSave(Player player){
        logger.info("After updating: {}", player.toString());
        player.encodePassword();
        playerRepository.save(player);
    }

    @HandleAfterDelete
    @Transactional
    public void handlePlayerPostDelete(Player player){
        logger.info("After deleting: {}", player.toString());
    }

    @HandleAfterLinkSave
    @Transactional
    public void handlePlayerPostLinkSave(Player player, Object o) {
        logger.info("After linking: {} to {}", player.toString(), o.toString());
    }
}
