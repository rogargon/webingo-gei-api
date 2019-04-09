package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Player invites;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @Column(name = "created_by")
    private Player createdBy;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @Column(name = "invites_to")
    private Game invitesTo;

    @Lob
    @NotBlank(message = "The message is mandatory")
    @Size(max = 255)
    private String message;
}
