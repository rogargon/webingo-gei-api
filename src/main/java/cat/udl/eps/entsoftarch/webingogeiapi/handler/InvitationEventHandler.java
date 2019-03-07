package cat.udl.eps.entsoftarch.webingogeiapi.handler;

import cat.udl.eps.entsoftarch.webingogeiapi.domain.Admin;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.repository.InvitationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthRootPaneUI;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Component
@RepositoryEventHandler
public class InvitationEventHandler {
    final Logger logger = LoggerFactory.getLogger(Admin.class);

    @Autowired
    InvitationRepository invitationRepository;

    @HandleBeforeCreate
    @Transactional
    public void handlePlayerPreCreate(Invitation invi) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        //invi.setCreatedAt(ZonedDateTime.now());
        //invi.setCreatedBy((Player) authentication.getPrincipal());

        //logger.info("Before creating: {}", invi.toString());
    }


}
