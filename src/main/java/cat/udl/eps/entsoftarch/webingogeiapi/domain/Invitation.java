package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
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


    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Game invitesTo;

    public Game getInvitesTo() { return invitesTo; }

    public void setInvitesTo(Game invitesTo) {
        this.invitesTo = invitesTo;
    }

    @NotNull
    @Lob
    @Size(max = 255, message = "error.description.size")
    private String message;

    public long getId() { return this.id; }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Player getInvites() {
        return invites;
    }

    public void setInvites(Player invites) {
        this.invites = invites;
    }

    public Player getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Player createdBy) {
        this.createdBy = createdBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
