package cat.udl.eps.entsoftarch.webingogeiapi.repository;


import cat.udl.eps.entsoftarch.webingogeiapi.domain.Invitation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long>  {
}
