package cat.udl.eps.entsoftarch.webingogeiapi.repository;


import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long>  {

    List<Invitation> findByMessageContaining(@Param("text") String text);
    List<Invitation> findById(@Param("id") long id);

    @PreAuthorize("#invites.username == principal.username")
    List<Invitation> findByInvites(@Param("invites") User invites);

    @PreAuthorize("#createdBy.username == principal.username")
    List<Invitation> findByCreatedBy(@Param("createdBy") Player createdBy);


}
