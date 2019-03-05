package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Player invites;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Player createdBy;

    //TODO: Uncomment Game implementations

    /*@ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Game invitesTo;*/

    private String message;

    public Player getInvites() { return invites; }

    public Player getCreatedBy() { return createdBy; }

    //public Game getInvitesTo() { return invitesTo; }

    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }

    /*public void setInvitesTo(Game invitesTo) {
    this.invitesTo = invitesTo;
    }*/

    public void setInvites(Player invites) {
        this.invites = invites;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendCreatedBy(Player message) {
        this.createdBy = createdBy;
    }

}
