package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.GameStatus;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.GameRepository;
import org.aspectj.lang.annotation.Before;
import org.mockito.MockitoAnnotations;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZonedDateTime;

@Component
@RepositoryEventHandler
public class GameEventHandler {
    final Logger logger = LoggerFactory.getLogger(Game.class);

    @Autowired
    GameRepository gameRepository;
    private Object mockMvc;
    private WebApplicationContext wac;


    @HandleBeforeCreate
    public void handleGamePreCreate(Game game) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        game.setStartAt((ZonedDateTime.now()));
    }


}

