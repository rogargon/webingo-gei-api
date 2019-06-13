package cat.udl.eps.entsoftarch.webingogeiapi.repository;


import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource
public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long>  {

    List<Invitation> findByMessageContainingIgnoreCase(@Param("text") String text);
    List<Invitation> findById(@Param("id") long id);

    @PreAuthorize("#invites.username == principal.username")
    Page<Invitation> findByInvites(@Param("invites") Player invites, Pageable p);

    @PreAuthorize("#createdBy.username == principal.username")
    Page<Invitation> findByCreatedBy(@Param("createdBy") Player createdBy, Pageable p);
}
