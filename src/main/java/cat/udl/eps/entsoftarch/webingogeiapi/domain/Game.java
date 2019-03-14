package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Data
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Game {

    @Id
    private int id;
    private int status;
    private float jackpot;
    private boolean bingo;
    private boolean line;
    private double pricePerCard;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime finishedAt, createdAt;
}