package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.GameStatus;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import cat.udl.eps.entsoftarch.webingogeiapi.exception.EditGameBadParam;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.AdminRepository;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import java.time.ZonedDateTime;
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
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class GameEventHandler {
    final Logger logger = LoggerFactory.getLogger(Game.class);

    @Autowired
    GameRepository gameRepository;

    @Autowired
    private AdminRepository adminRepository;

    @HandleBeforeCreate
    public void handleGamePreCreate(Game game) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        game.setCreator(adminRepository.findById(user.getUsername()).orElse(null));
        if(game.getPricePerCard() == null){
            game.setPricePerCard(0.0);
        }else if(game.getPricePerCard() <= 0.0){
            throw new EditGameBadParam();
        }
        game.setStatus(GameStatus.LOADING);
        game.setJackpot(0.0);
        game.setCreatedAt(ZonedDateTime.now());
        //game.setNumbers();
    }

    @HandleBeforeSave
    public void handleGamePreSave(Game game){
        logger.info("Before updating: {}", game.toString());
        if(game.getStatus() == GameStatus.FINISHED && !game.isBingo()){
            throw new EditGameBadParam(); //meanwhile
        }
        if(!game.isLine() && game.isBingo()){
            throw new EditGameBadParam(); //meanwhile
        }
        if(game.getPricePerCard() < 0.0){
            throw new EditGameBadParam();
        }
        if(gameRepository.findById(game.getId()).get().getStatus().toString() == "LOADING" && game.getStatus().toString() == "PLAYING"){
            game.setStartAt(ZonedDateTime.now());
        }
    }

    @HandleBeforeDelete
    public void handleGamePreDelete(Game game) {
        if (game.getStatus().equals(GameStatus.PLAYING))
            throw new AuthorizationServiceException("Playing game cannot be deleted");
    }

    @HandleBeforeLinkSave
    public void handleGamePreLinkSave(Game game, Object o) {
        logger.info("Before linking: {} to {}", game.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleGamePostCreate(Game game){
        logger.info("After creating: {}", game.toString());
        gameRepository.save(game);
    }

    @HandleAfterSave
    public void handleGamePostSave(Game game){
        logger.info("After updating: {}", game.toString());
        gameRepository.save(game);
    }

    @HandleAfterDelete
    public void handleGamePostDelete(Game game){
        logger.info("After deleting: {}", game.toString());
    }

    @HandleAfterLinkSave
    public void handleGamePostLinkSave(Game game, Object o) {
        logger.info("After linking: {} to {}", game.toString(), o.toString());
    }




}
