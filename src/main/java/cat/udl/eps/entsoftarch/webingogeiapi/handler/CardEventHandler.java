package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Card;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler

public class CardEventHandler {
    final Logger logger = LoggerFactory.getLogger(Card.class);

    @Autowired
    CardRepository CardRepository;

    @HandleBeforeCreate
    public void handleCardBeforeCreate(Card card){
        card.generateCard();
        logger.info("After creating: {}", card.toString());
        CardRepository.save(card);
    }

}
