package cat.udl.eps.entsoftarch.webingogeiapi.repository;


import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long>  {

    List<Invitation> findByMessageContaining(@Param("text") String text);
}
